/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.NUMBER;
import static org.junit.Assert.assertEquals;

public class NumberTypeTest extends TypeTest {
    @Test
    public void test_concat_NUMBER() {
        assertEquals(objOf(12L), NUMBER.concat(8L, 4L));
        assertEquals(objOf(120L), NUMBER.concat(80L, 40L));
    }

    @Test
    public void test_remove_NUMBER() {
        assertEquals(objOf(8L), NUMBER.remove(12L, 4L));
        assertEquals(objOf(0L), NUMBER.remove(8L, 8L));
        assertEquals(objOf(-4L), NUMBER.remove(8L, 12L));
    }

    @Test
    public void test_convert_NUMBER() {
        assertEquals(objOf(125L), NUMBER.convert("125"));
        assertEquals(objOf(125L), NUMBER.convert(125L));
        assertEquals(objOf(1L), NUMBER.convert(1.25d));
        assertEquals(objOf(2L), NUMBER.convert(1.75d));
        assertEquals(objOf(675L), NUMBER.convert(list("125", "225", "325")));
        assertEquals(objOf(675L), NUMBER.convert(set("125", "225", "325")));
        assertEquals(objOf(675L), NUMBER.convert(list(125L, 225L, 325L)));
        assertEquals(objOf(675L), NUMBER.convert(set(125L, 225L, 325L)));
        assertEquals(objOf(6L), NUMBER.convert(list(1.25d, 2.25d, 3.25d)));
        assertEquals(objOf(6L), NUMBER.convert(set(1.25d, 2.25d, 3.25d)));
        assertEquals(objOf(9L), NUMBER.convert(list(1.75d, 2.75d, 3.75d)));
        assertEquals(objOf(9L), NUMBER.convert(set(1.75d, 2.75d, 3.75d)));
    }
}
