package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.SET_OF_NUMBER;
import static org.junit.Assert.assertEquals;

public class SetOfNumberTypeTest extends TypeTest {
    @Test
    public void test_concat_SET_NUMBER() {
        assertEquals(set(1L, 2L, 3L, 4L, 5L, 6L), SET_OF_NUMBER.concat(set(1L, 2L, 3L, 4L), set(3L, 4L, 5L, 6L)));
        assertEquals(set(10L, 20L, 30L, 40L, 50L, 60L), SET_OF_NUMBER.concat(set(10L, 20L, 30L, 40L), set(30L, 40L, 50L, 60L)));
    }

    @Test
    public void test_remove_SET_NUMBER() {
        assertEquals(set(1L, 2L, 5L, 6L), SET_OF_NUMBER.remove(set(1L, 2L, 3L, 4L, 5L, 6L), set(3L, 4L)));
        assertEquals(set(), SET_OF_NUMBER.remove(set(1L, 2L, 3L, 4L), set(1L, 2L, 3L, 4L)));
        assertEquals(set(1L, 2L, 3L, 4L, 5L, 6L), SET_OF_NUMBER.remove(set(1L, 2L, 3L, 4L, 5L, 6L), set(7L, 8L, 9L)));
    }

    @Test
    public void test_convert_SET_NUMBER() {
        assertEquals(set(125L, 225L), SET_OF_NUMBER.convert("125,225,125"));
        assertEquals(set(125L), SET_OF_NUMBER.convert(list(125L)));
        assertEquals(set(1L), SET_OF_NUMBER.convert(list(1.25d)));
        assertEquals(set(2L), SET_OF_NUMBER.convert(list(1.75d)));
        assertEquals(set(125L, 225L, 325L), SET_OF_NUMBER.convert(list("125", "225", "325", "125")));
        assertEquals(set(125L, 225L, 325L), SET_OF_NUMBER.convert(set("125", "225", "325")));
        assertEquals(set(125L, 225L, 325L), SET_OF_NUMBER.convert(list(125L, 225L, 325L)));
        assertEquals(set(125L, 225L, 325L), SET_OF_NUMBER.convert(set(125L, 225L, 325L)));
        assertEquals(set(1L, 2L, 3L), SET_OF_NUMBER.convert(list(1.25d, 2.25d, 3.25d, 1.25d)));
        assertEquals(set(1L, 2L, 3L), SET_OF_NUMBER.convert(set(1.25d, 2.25d, 3.25d)));
        assertEquals(set(2L, 3L, 4L), SET_OF_NUMBER.convert(list(1.75d, 2.75d, 3.75d, 1.75d)));
        assertEquals(set(2L, 3L, 4L), SET_OF_NUMBER.convert(set(1.75d, 2.75d, 3.75d)));
    }
}
