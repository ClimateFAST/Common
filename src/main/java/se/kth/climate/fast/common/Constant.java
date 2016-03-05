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

import java.nio.ByteBuffer;

/**
 *
 * @author lkroll
 */
public class Constant extends Variable {

    private byte[] value;

    /**
     * no param constr for AVRO
     */
    public Constant() {
        
    }
    
    Constant(Variable var) {
        super(var);
    }

    /**
     * @return the value
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(byte[] value) {
        this.value = value;
    }

    public double getAsDouble() {
        return ByteBuffer.wrap(value).getDouble();
    }

    public void set(double v) {
        value = ByteBuffer.allocate(Double.SIZE / 8).putDouble(v).array();
    }

    public float getAsFloat() {
        return ByteBuffer.wrap(value).getFloat();
    }

    public void set(float v) {
        value = ByteBuffer.allocate(Float.SIZE / 8).putFloat(v).array();
    }

    public long getAsLong() {
        return ByteBuffer.wrap(value).getLong();
    }

    public void set(long v) {
        value = ByteBuffer.allocate(Long.SIZE / 8).putLong(v).array();
    }

    public int getAsInt() {
        return ByteBuffer.wrap(value).getInt();
    }

    public void set(int v) {
        value = ByteBuffer.allocate(Integer.SIZE / 8).putInt(v).array();
    }

    public boolean getAsBool() {
        return value[0] != 0;
    }

    public void set(boolean v) {
        if (v) {
            value = new byte[]{1};
        } else {
            value = new byte[]{0};
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    @Override
    public void toString(StringBuilder sb) {
        sb.append("Constant(\n");
        toStringFields(sb);
        sb.append("value: ");
        if (value == null) {
            sb.append("null");
        } else {
            switch (this.getDataType()) {
                case DOUBLE:
                    sb.append(this.getAsDouble());
                    break;
                case FLOAT:
                    sb.append(this.getAsFloat());
                    break;
                case LONG:
                    sb.append(this.getAsLong());
                    break;
                case INT:
                    sb.append(this.getAsInt());
                    break;
                case BOOLEAN:
                    sb.append(this.getAsBool());
                    break;
                default:
                    sb.append(ByteBuffer.wrap(value));
            }
        }
        sb.append('\n');
        sb.append(")");
    }
}
