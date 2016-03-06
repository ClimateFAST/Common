/*
 * Copyright (C) 2016 KTH Royal Institute of Technology
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package se.kth.climate.fast.common;

import com.google.common.collect.ArrayListMultimap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author lkroll
 */
public class MetadataBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(MetadataBuilder.class);

    private final ArrayList<Dimension> dimensions = new ArrayList<>();
    private final ArrayList<Variable> vars = new ArrayList<>();
    private final ArrayList<Constant> constants = new ArrayList<>();
    private final HashMap<String, String> attributes = new HashMap<>();

    public Metadata build() {
        Metadata meta = new Metadata();
        meta.setDimensions(dimensions.toArray(new Dimension[0]));
        meta.setVariables(vars.toArray(new Variable[0]));
        meta.setConstants(constants.toArray(new Constant[0]));
        meta.setAttributes(new HashMap(attributes));
        return meta;
    }

    public void addDimension(DimensionBuilder dim) {
        Dimension d = dim.build();
        dimensions.add(d);
    }
    
    public void addDimension(Dimension dim) {
        dimensions.add(dim);
    }

    public void addVariable(VariableBuilder var) {
        vars.add(var.build());
    }

    private void addVariable(Variable var) {
        vars.add(var);
    }

    public Constant addConstant(VariableBuilder var) {
        Constant c = var.buildConstant();
        constants.add(c);
        return c;
    }

    private void addConstant(Constant c) {
        constants.add(c);
    }

    public void addAttribute(String name, String value) {
        if (value != null) {
            attributes.put(name, value);
        } else {
            attributes.put(name, ""); // because nulls suck
        }
    }

    public static Metadata merge(List<Metadata> metas) {
        Set<String> vars = new HashSet<>();
        Set<String> consts = new HashSet<>();
        HashMap<String, String> attrs;
        MetadataBuilder mb = new MetadataBuilder();
        
        ArrayListMultimap<String, Dimension> dimensions = ArrayListMultimap.create();
        for (Metadata mi : metas) {
            for (Dimension dim : mi.getDimensions()) {
                dimensions.put(dim.getName(), dim);
            }
        }
        Metadata m0 = metas.get(0);
        for (Variable v : m0.getVariables()) {
            mb.addVariable(v);
            vars.add(v.getStandardName());
        }
        for (Constant c : m0.getConstants()) {
            mb.addConstant(c);
            consts.add(c.getStandardName());
        }
        attrs = new HashMap<>(m0.getAttributes());
        for (int i = 1; i < metas.size(); i++) {
            Metadata mi = metas.get(i);
            for (Variable v : mi.getVariables()) {
                if (!vars.contains(v.getStandardName())) {
                    LOG.warn("During merge: Variable {} not present in all files!", v.getStandardName());
                }
            }
            for (Constant c : mi.getConstants()) {
                if (!consts.contains(c.getStandardName())) {
                    LOG.warn("During merge: Constant {} not present in all files!", c.getStandardName());
                }
            }
            for (Entry<String, String> e : mi.getAttributes().entrySet()) {
                if (attrs.containsKey(e.getKey())) {
                    String presentValue = attrs.get(e.getKey());
                    String newValue = e.getValue();
                    if (!presentValue.equals(newValue)) {
                        String mergedValue = presentValue + " | " + newValue;
                        attrs.put(e.getKey(), mergedValue);
                        LOG.info("Merged values {} and {} into {}", new Object[]{presentValue, newValue, mergedValue});
                    } // else ignore
                } else {
                    attrs.put(e.getKey(), e.getValue());
                }
            }
        }
        for (Entry<String, Collection<Dimension>> e : dimensions.asMap().entrySet()) {
            String name = e.getKey();
            Collection<Dimension> dims = e.getValue();
            if (dims.size() != metas.size()) {
                LOG.warn("During merge: Dimension {} not present in all files!", name);
//                System.out.println("Collected Dims: ");
//                for (Dimension d : dims) {
//                    System.out.println(d);
//                }
//                System.out.println("Metas: ");
//                for (Metadata m : metas) {
//                    System.out.println(m);
//                }
            }
            Dimension dim0 = dims.iterator().next();
            if (dim0.isUnlimited()) { // merge sizes
                long accum = 0;
                for (Dimension d : dims) {
                    accum += d.getSize();
                }
                DimensionBuilder db = new DimensionBuilder();
                db.setName(name);
                db.setUnlimited(true);
                db.setSize(accum);
                mb.addDimension(db);
            } else { // just take any since they should be all the same anyway
                mb.addDimension(dim0);
            }
        }
        for (Entry<String, String> e : attrs.entrySet()) {
            mb.addAttribute(e.getKey(), e.getValue());
        }
        return mb.build();
    }
}
