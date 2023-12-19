/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.StringType;
import org.junit.After;
import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.SET_OF_STRING;
import static org.junit.Assert.assertEquals;

public class SetOfStringTypeTest extends TypeTest {
    @After
    public void teardown() {
        StringType.setSeparator(StringType.DEFAULT_SEPARATOR);
    }

    @Test
    public void test_concat_SET_STRING() {
        assertEquals(set("A", "B", "C", "D", "E", "F"), SET_OF_STRING.concat(set("A", "B", "C", "D"), set("C", "D", "E", "F")));
        assertEquals(set("1", "2", "3", "4", "5", "6"), SET_OF_STRING.concat(set("1", "2", "3", "4"), set("3", "4", "5", "6")));
    }

    @Test
    public void test_remove_SET_STRING() {
        assertEquals(set("A", "B", "E", "F"), SET_OF_STRING.remove(set("A", "B", "C", "D", "E", "F"), set("C", "D")));
        assertEquals(set(), SET_OF_STRING.remove(set("A", "B", "C", "D"), set("A", "B", "C", "D")));
        assertEquals(set("A", "B", "C", "D", "E", "F"), SET_OF_STRING.remove(set("A", "B", "C", "D", "E", "F"), set("G", "H", "I")));
    }

    @Test
    public void test_convert_SET_STRING() {
        assertEquals(set("ABC", "DEF", "GHI"), SET_OF_STRING.convert(set("ABC", "DEF", "GHI")));
        assertEquals(set("ABC", "DEF", "GHI"), SET_OF_STRING.convert("ABC,DEF,GHI,ABC"));
        assertEquals(set("125"), SET_OF_STRING.convert(125L));
        assertEquals(set("1.25"), SET_OF_STRING.convert(1.25d));
        assertEquals(set("125", "225"), SET_OF_STRING.convert(list(125L, 225L, 125L)));
        assertEquals(set("1.25", "2.25", "3.25"), SET_OF_STRING.convert(list(1.25d, 2.25d, 3.25d, 1.25d)));
        assertEquals(set("125", "225"), SET_OF_STRING.convert(set(125L, 225L)));
        assertEquals(set("1.25", "2.25", "3.25"), SET_OF_STRING.convert(set(1.25d, 2.25d, 3.25d)));
    }

    @Test
    public void test_convert_SET_STRING_different_separators() {
        String PUNCTUATIONS = "`~!@#$%^&*()_+{}|:\"<>?-=[];'.\\/,'";

        for (int i = 0; i < PUNCTUATIONS.length(); i++) {
            char separator = PUNCTUATIONS.charAt(i);
            StringType.setSeparator(separator);
            assertEquals(set("ABC", "DEF", "GHI"), SET_OF_STRING.convert("ABC" + separator + "DEF" + separator + "GHI" + separator + "ABC"));
        }
    }
}
