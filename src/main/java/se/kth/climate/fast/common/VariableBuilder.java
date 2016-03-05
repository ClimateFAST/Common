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

/**
 *
 * @author lkroll
 */
public class VariableBuilder {

    private Variable var = new Variable();
    private HashMap<String, String> attributes = new HashMap<>();

    public void setShortName(String shortName) {
        var.setShortName(shortName);
    }

    public void setStandardName(String standardName) {
        var.setStandardName(standardName);
    }

    public void setLongName(String longName) {
        var.setLongName(longName);
    }

    public void setUnit(String unit) {
        var.setUnits(unit);
    }

    public void setType(ucar.ma2.DataType type) {
        var.setType(new DataType(type));
    }

    public void addAttribute(String name, String value) {
        if (value != null) {
            attributes.put(name, value);
        } else {
            attributes.put(name, ""); // because nulls suck
        }
    }

    Variable build() {
        var.setAttributes(attributes);
        Variable v = var;
        var = new Variable();
        attributes = new HashMap<>();
        return v;
    }

    Constant buildConstant() {
        Constant c = new Constant(var);
        c.setAttributes(attributes);
        var = new Variable();
        attributes = new HashMap<>();
        return c;
    }

}
