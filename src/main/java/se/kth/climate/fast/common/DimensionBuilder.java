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
public class DimensionBuilder {
    
    private Dimension d = new Dimension();
    
    /**
     * @param name the name to set
     */
    void setName(String name) {
        d.setName(name);
    }

    /**
     * @param unlimited the unlimited to set
     */
    void setUnlimited(boolean unlimited) {
        d.setUnlimited(unlimited);
    }

    /**
     * @param size the size to set
     */
    void setSize(long size) {
        d.setSize(size);
    }
    
    public Dimension build() {
        Dimension r = d;
        d = new Dimension();
        return d;
    }
}
