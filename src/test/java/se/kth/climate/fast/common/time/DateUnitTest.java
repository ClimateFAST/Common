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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 *
 * @author lkroll
 */
@RunWith(JUnit4.class)
public class DateUnitTest {

    @Test
    public void defaultTest() {
        DateUnit unit = DateUnit.DEFAULT;
        //String unitS = unit.toString();
        String unitS2 = unit.getUnitS();
        //assertEquals(unitS, unitS2);
        System.out.println("1: " + unit + " -- " + unit.getUnit().toString());
        DateUnit unit3 = new DateUnit(unit.getCal(), unitS2);
        assertNotNull(unit3);
        System.out.println("3: " + unit3 + " -- " + unit3.getUnit().toString());

        byte[] bytes = null;
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(unit);
            bytes = baos.toByteArray();
        } catch (IOException ex) {
            Logger.getLogger(DateUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (bytes == null) {
            fail("No data!");
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bais)) {
            DateUnit unit4 = (DateUnit) ois.readObject();
            assertNotNull(unit4);
            System.out.println("4: " + unit4 + " -- " + unit4.getUnit().toString());
        } catch (IOException ex) {
            Logger.getLogger(DateUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DateUnitTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
