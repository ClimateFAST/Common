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
import org.apache.avro.reflect.Nullable;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.Stringable;

/**
 *
 * @author lkroll
 */
public class Variable {

    public static final Schema AVRO = ReflectData.get().getSchema(Variable.class);

    private String shortName;
    private String standardName;
    private String longName;
    @Nullable
    private String units;
    @Stringable
    private DataType type;
    private HashMap<String, String> attributes;

    public Variable() {
        // no args for avro
    }

    Variable(Variable other) {
        this.shortName = other.shortName;
        this.standardName = other.standardName;
        this.longName = other.longName;
        this.units = other.units;
        this.type = other.type;
        this.attributes = other.attributes;
    }

    public static boolean includeAttribute(String attrName) {
        switch (attrName) {
            case "standard_name":
                return false;
            case "long_name":
                return false;
            case "units":
                return false;
            default:
                return true;
        }
    }

    /**
     * @return the shortName
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * @param shortName the shortName to set
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * @return the standardName
     */
    public String getStandardName() {
        return standardName;
    }

    /**
     * @param standardName the standardName to set
     */
    public void setStandardName(String standardName) {
        this.standardName = standardName;
    }

    /**
     * @return the longName
     */
    public String getLongName() {
        return longName;
    }

    /**
     * @param longName the longName to set
     */
    public void setLongName(String longName) {
        this.longName = longName;
    }

    /**
     * @return the units
     */
    public String getUnits() {
        return units;
    }

    /**
     * @param units the units to set
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     * @return the type
     */
    public DataType getType() {
        return type;
    }

    public ucar.ma2.DataType getDataType() {
        return this.type.type;
    }

    /**
     * @param type the type to set
     */
    public void setType(DataType type) {
        this.type = type;
    }

    /**
     * @return the attributes
     */
    public HashMap<String, String> getAttributes() {
        return attributes;
    }

    /**
     * @param attributes the attributes to set
     */
    public void setAttributes(HashMap<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    public void toString(StringBuilder sb) {
        sb.append("Variable(\n");
        toStringFields(sb);
        sb.append(")");
    }

    protected void toStringFields(StringBuilder sb) {
        sb.append("short_name: ");
        sb.append(this.shortName);
        sb.append('\n');
        sb.append("standard_name: ");
        sb.append(this.standardName);
        sb.append('\n');
        sb.append("long_name: ");
        sb.append(this.longName);
        sb.append('\n');
        sb.append("units: ");
        sb.append(this.units);
        sb.append('\n');
        sb.append("type: ");
        sb.append(this.type);
        sb.append('\n');
        sb.append("attributes: ");
        sb.append(this.attributes);
        sb.append('\n');
    }
}
