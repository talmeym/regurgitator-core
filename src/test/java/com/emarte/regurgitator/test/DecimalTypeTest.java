/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.DECIMAL;
import static org.junit.Assert.assertEquals;

public class DecimalTypeTest extends TypeTest {
    @Test
    public void test_concat_DECIMAL() {
        assertEquals(objOf(12d), DECIMAL.concat(7.5d, 4.5d));
        assertEquals(objOf(111d), DECIMAL.concat(70.5d, 40.5d));
    }

    @Test
    public void test_remove_DECIMAL() {
        assertEquals(objOf(8.0d), DECIMAL.remove(12.5d, 4.5d));
        assertEquals(objOf(0d), DECIMAL.remove(8.5d, 8.5d));
        assertEquals(objOf(-4.0d), DECIMAL.remove(8.5d, 12.5d));
    }

    @Test
    public void test_convert_DECIMAL() {
        assertEquals(objOf(1.25d), DECIMAL.convert("1.25"));
        assertEquals(objOf(125d), DECIMAL.convert(125L));
        assertEquals(objOf(1.25d), DECIMAL.convert(1.25d));
        assertEquals(objOf(6.75d), DECIMAL.convert(list("1.25", "2.25", "3.25")));
        assertEquals(objOf(6.75d), DECIMAL.convert(set("1.25", "2.25", "3.25")));
        assertEquals(objOf(6.75d), DECIMAL.convert(list(1.25d, 2.25d, 3.25d)));
        assertEquals(objOf(6.75d), DECIMAL.convert(set(1.25d, 2.25d, 3.25d)));
        assertEquals(objOf(675d), DECIMAL.convert(list(125L, 225L, 325L)));
        assertEquals(objOf(675d), DECIMAL.convert(set(125L, 225L, 325L)));
    }
}
