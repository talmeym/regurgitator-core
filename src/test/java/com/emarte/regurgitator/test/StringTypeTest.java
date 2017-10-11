/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static org.junit.Assert.assertEquals;

public class StringTypeTest extends TypeTest {
    @Test
    public void test_concat_STRING() {
        assertEquals("ABCDEF", STRING.concat("ABC", "DEF"));
        assertEquals("123456", STRING.concat("123", "456"));
    }

    @Test
    public void test_remove_STRING() {
        assertEquals("ABEF", STRING.remove("ABCDEF", "CD"));
        assertEquals("", STRING.remove("ABCD", "ABCD"));
        assertEquals("ABCDEF", STRING.remove("ABCDEF", "GHI"));
    }

    @Test
    public void test_convert_STRING() {
        assertEquals("ABC", STRING.convert("ABC"));
        assertEquals("125", STRING.convert(125L));
        assertEquals("1.25", STRING.convert(1.25d));
        assertEquals("ABC,DEF", STRING.convert(list("ABC", "DEF")));
        assertEquals("ABC,DEF,GHI", STRING.convert(set("ABC", "DEF", "GHI")));
        assertEquals("125,225", STRING.convert(list(125L, 225L)));
        assertEquals("125,225,325", STRING.convert(set(125L, 225L, 325L)));
        assertEquals("1.25,2.25", STRING.convert(list(1.25d, 2.25d)));
        assertEquals("1.25,2.25,3.25", STRING.convert(set(1.25d, 2.25d, 3.25d)));
    }
}
