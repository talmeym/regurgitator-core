/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.After;
import org.junit.Test;
import uk.emarte.regurgitator.core.StringType;

import static org.junit.Assert.assertEquals;
import static uk.emarte.regurgitator.core.CoreTypes.STRING;

public class StringTypeTest extends TypeTest {
    @After
    public void teardown() {
        StringType.setSeparator(StringType.DEFAULT_SEPARATOR);
    }

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
        testSeparator(StringType.DEFAULT_SEPARATOR);
    }

    @Test
    public void test_convert_STRING_different_separators() {
        String PUNCTUATIONS = "`~!@#$%^&*()_+{}|:\"<>?-=[];'.\\/,'";

        for (int i = 0; i < PUNCTUATIONS.length(); i++) {
            testSeparator(PUNCTUATIONS.charAt(i));
        }
    }

    private void testSeparator(char separator) {
        StringType.setSeparator(separator);
        assertCollectionConversion(separator);
    }

    private void assertCollectionConversion(char separator) {
        assertEquals("ABC" + separator + "DEF", STRING.convert(list("ABC", "DEF")));
        assertEquals("ABC" + separator + "DEF" + separator + "GHI", STRING.convert(set("ABC", "DEF", "GHI")));
        assertEquals("125" + separator + "225", STRING.convert(list(125L, 225L)));
        assertEquals("125" + separator + "225" + separator + "325", STRING.convert(set(125L, 225L, 325L)));
        assertEquals("1.25" + separator + "2.25", STRING.convert(list(1.25d, 2.25d)));
        assertEquals("1.25" + separator + "2.25" + separator + "3.25", STRING.convert(set(1.25d, 2.25d, 3.25d)));
    }
}
