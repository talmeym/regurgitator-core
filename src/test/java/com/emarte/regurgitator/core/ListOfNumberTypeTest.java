package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.LIST_OF_NUMBER;
import static org.junit.Assert.assertEquals;

public class ListOfNumberTypeTest extends TypeTest {
	@Test
	public void test_concat_LIST_NUMBER() {
		assertEquals(list(1L, 2L, 3L, 4L, 5L, 6L), LIST_OF_NUMBER.concat(list(1L, 2L, 3L), list(4L, 5L, 6L)));
		assertEquals(list(10L, 20L, 30L, 40L, 50L, 60L), LIST_OF_NUMBER.concat(list(10L, 20L, 30L), list(40L, 50L, 60L)));
	}

	@Test
	public void test_remove_LIST_NUMBER() {
		assertEquals(list(1L, 2L, 5L, 6L), LIST_OF_NUMBER.remove(list(1L, 2L, 3L, 4L, 5L, 6L), list(3L, 4L)));
		assertEquals(list(), LIST_OF_NUMBER.remove(list(1L, 2L, 3L, 4L), list(1L, 2L, 3L, 4L)));
		assertEquals(list(1L, 2L, 3L, 4L, 5L, 6L), LIST_OF_NUMBER.remove(list(1L, 2L, 3L, 4L, 5L, 6L), list(7L, 8L, 9L)));
	}

	@Test
	public void test_convert_LIST_NUMBER() {
		assertEquals(list(125L, 225L), LIST_OF_NUMBER.convert("125,225"));
		assertEquals(list(125L), LIST_OF_NUMBER.convert(list(125L)));
		assertEquals(list(1L), LIST_OF_NUMBER.convert(list(1.25d)));
		assertEquals(list(2L), LIST_OF_NUMBER.convert(list(1.75d)));
		assertEquals(list(125L, 225L, 325L), LIST_OF_NUMBER.convert(list("125", "225", "325")));
		assertEquals(list(125L, 225L, 325L), LIST_OF_NUMBER.convert(set("125", "225", "325")));
		assertEquals(list(125L, 225L, 325L), LIST_OF_NUMBER.convert(list(125L, 225L, 325L)));
		assertEquals(list(125L, 225L, 325L), LIST_OF_NUMBER.convert(set(125L, 225L, 325L)));
		assertEquals(list(1L, 2L, 3L), LIST_OF_NUMBER.convert(list(1.25d, 2.25d, 3.25d)));
		assertEquals(list(1L, 2L, 3L), LIST_OF_NUMBER.convert(set(1.25d, 2.25d, 3.25d)));
		assertEquals(list(2L, 3L, 4L), LIST_OF_NUMBER.convert(list(1.75d, 2.75d, 3.75d)));
		assertEquals(list(2L, 3L, 4L), LIST_OF_NUMBER.convert(set(1.75d, 2.75d, 3.75d)));
	}
}
