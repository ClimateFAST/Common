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

/**
 *
 * @author lkroll
 */
public class Dimension {
    
    private String name;
    private boolean unlimited;
    private long size;
    
    /**
     * no param constr for AVRO
     */
    public Dimension() {
        
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    void setName(String name) {
        this.name = name;
    }

    /**
     * @return the unlimited
     */
    public boolean isUnlimited() {
        return unlimited;
    }

    /**
     * @param unlimited the unlimited to set
     */
    void setUnlimited(boolean unlimited) {
        this.unlimited = unlimited;
    }

    /**
     * @return the size
     */
    public long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    void setSize(long size) {
        this.size = size;
    }

     @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        toString(sb);
        return sb.toString();
    }

    public void toString(StringBuilder sb) {
        sb.append("Dimension(\n");
        toStringFields(sb);
        sb.append(")");
    }

    protected void toStringFields(StringBuilder sb) {
        sb.append("name: ");
        sb.append(this.name);
        sb.append('\n');        
        sb.append("unlimited?: ");
        sb.append(this.unlimited);
        sb.append('\n');
        sb.append("size: ");
        sb.append(this.size);
        sb.append('\n');
    }
}
