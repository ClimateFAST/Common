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
package se.kth.climate.fast.common.time;

import java.io.Serializable;
import ucar.nc2.time.Calendar;
import ucar.nc2.time.CalendarDate;
import ucar.nc2.time.CalendarDateUnit;
import ucar.nc2.time.CalendarPeriod;

/**
 *
 * @author lkroll
 */
public class DateUnit implements Serializable {
    
    public static final DateUnit DEFAULT = new DateUnit(Calendar.gregorian, "millisec since 1970-01-01t00:00:00z");

    private final Calendar cal;
    private final String unitS;
    //
    private transient CalendarDateUnit unit;

    public DateUnit(Calendar cal, String unitS) {
        this.cal = cal;
        this.unitS = unitS;
    }

    /**
     * @return the cal
     */
    public Calendar getCal() {
        return cal;
    }

    /**
     * @return the unitS
     */
    public String getUnitS() {
        return unitS;
    }

    /**
     * @return the unit
     */
    public CalendarDateUnit getUnit() {
        if (unit == null) {
            unit = CalendarDateUnit.withCalendar(cal, unitS);
        }
        return unit;
    }

    /**
     * Forwarded from {@link ucar.nc2.time.CalendarDateUnit.makeCalendarDate(double)}
     * 
     * @param value
     * @return 
     */
    public CalendarDate makeCalendarDate(double value) {
        return getUnit().makeCalendarDate(value);
    }

    /**
     * Forwarded from {@link ucar.nc2.time.CalendarDateUnit.makeCalendarDate(int)}
     * 
     * @param value
     * @return 
     */
    public CalendarDate makeCalendarDate(int value) {
        return getUnit().makeCalendarDate(value);
    }

    @Override
    public String toString() {
        return "calendar " + cal + " " + unitS;
    }

    /**
     * Forwarded from {@link ucar.nc2.time.CalendarDateUnit.getBaseCalendarDate()}
     * 
     * @return 
     */
    public CalendarDate getBaseCalendarDate() {
        return getUnit().getBaseCalendarDate();
    }

    /**
     * Forwarded from {@link ucar.nc2.time.CalendarDateUnit.getTimeUnit()}
     * 
     * @return 
     */
    public CalendarPeriod getTimeUnit() {
        return getUnit().getTimeUnit();
    }

    /**
     * Forwarded from {@link ucar.nc2.time.CalendarDateUnit.getCalendar()}
     * 
     * @return 
     */
    public Calendar getCalendar() {
        return getUnit().getCalendar();
    }

}
