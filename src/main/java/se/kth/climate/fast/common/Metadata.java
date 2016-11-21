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

import java.util.HashMap;
import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;

/**
 *
 * @author lkroll
 */
public class Metadata {

    public static final Schema AVRO = ReflectData.get().getSchema(Metadata.class);

    private Dimension[] dimensions;
    private Variable[] variables;
    private Constant[] constants;
    private HashMap<String, String> attributes;

    void setVariables(Variable[] vars) {
        this.variables = vars;
    }

    public Variable[] getVariables() {
        return variables;
    }

    public Variable findVariable(String name) {
        for (Variable v : variables) {
            if (v.getStandardName().equals(name)
                    || v.getShortName().equals(name)
                    || v.getLongName().equals(name)) {
                return v;
            }
        }
        for (Constant c : constants) {
            if (c.getStandardName().equals(name)
                    || c.getShortName().equals(name)
                    || c.getLongName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * @return the constants
     */
    public Constant[] getConstants() {
        return constants;
    }

    /**
     * @param constants the constants to set
     */
    void setConstants(Constant[] constants) {
        this.constants = constants;
    }

    /**
     * @return the attributes
     */
    public HashMap<String, String> getAttributes() {
        return attributes;
    }
    
    public String getAttribute(String key) {
        return attributes.get(key);
    }

    /**
     * @param attributes the attributes to set
     */
    void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    /**
     * @return the dimensions
     */
    public Dimension[] getDimensions() {
        return dimensions;
    }
    
    public Dimension findDimension(String name) {
        for (Dimension d : dimensions) {
            if (d.getName().equals(name)) {
                return d;
            }
        }
        return null;
    }

    /**
     * @param dimensions the dimensions to set
     */
    void setDimensions(Dimension[] dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Metadata {\n");
        sb.append("dimensions: ");
        for (Dimension d : dimensions) {
            d.toString(sb);
            sb.append('\n');
        }
        sb.append('\n');
        sb.append("variables: [\n");
        for (Variable v : variables) {
            v.toString(sb);
            sb.append('\n');
        }
        sb.append("]\n");
        sb.append("constants: [\n");
        for (Constant c : constants) {
            c.toString(sb);
            sb.append('\n');
        }
        sb.append("]\n");
        sb.append("attrs: \n");
        sb.append(attributes);
        sb.append("\n}");
        return sb.toString();
    }
}
