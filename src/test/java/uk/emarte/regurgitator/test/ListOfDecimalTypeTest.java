/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static uk.emarte.regurgitator.core.CoreTypes.LIST_OF_DECIMAL;

public class ListOfDecimalTypeTest extends TypeTest {
    @Test
    public void test_concat_LIST_DECIMAL() {
        assertEquals(list(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), LIST_OF_DECIMAL.concat(list(1.5d, 2.5d, 3.5d), list(4.5d, 5.5d, 6.5d)));
        assertEquals(list(10.5d, 20.5d, 30.5d, 40.5d, 50.5d, 60.5d), LIST_OF_DECIMAL.concat(list(10.5d, 20.5d, 30.5d), list(40.5d, 50.5d, 60.5d)));
    }

    @Test
    public void test_remove_LIST_DECIMAL() {
        assertEquals(list(1.5d, 2.5d, 5.5d, 6.5d), LIST_OF_DECIMAL.remove(list(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), list(3.5d, 4.5d)));
        assertEquals(list(), LIST_OF_DECIMAL.remove(list(1.5d, 2.5d, 3.5d, 4.5d), list(1.5d, 2.5d, 3.5d, 4.5d)));
        assertEquals(list(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), LIST_OF_DECIMAL.remove(list(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), list(7.5d, 8.5d, 9.5d)));
    }

    @Test
    public void test_convert_LIST_DECIMAL() {
        assertEquals(list(1.25d, 2.25d), LIST_OF_DECIMAL.convert("1.25,2.25"));
        assertEquals(list(125d), LIST_OF_DECIMAL.convert(list(125L)));
        assertEquals(list(1.25d), LIST_OF_DECIMAL.convert(list(1.25d)));
        assertEquals(list(1.25d, 2.25d, 3.25d), LIST_OF_DECIMAL.convert(list("1.25", "2.25", "3.25")));
        assertEquals(list(1.25d, 2.25d, 3.25d), LIST_OF_DECIMAL.convert(set("1.25", "2.25", "3.25")));
        assertEquals(list(125d, 225d, 325d), LIST_OF_DECIMAL.convert(list(125L, 225L, 325L)));
        assertEquals(list(125d, 225d, 325d), LIST_OF_DECIMAL.convert(set(125L, 225L, 325L)));
        assertEquals(list(1.25d, 2.25d, 3.25d), LIST_OF_DECIMAL.convert(list(1.25d, 2.25d, 3.25d)));
        assertEquals(list(1.25d, 2.25d, 3.25d), LIST_OF_DECIMAL.convert(set(1.25d, 2.25d, 3.25d)));
    }
}
