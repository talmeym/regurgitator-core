package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.LIST_OF_NUMBER;
import static org.junit.Assert.assertEquals;

public class ListOfNumberTypeTest extends TypeTest {
	@Test
	public void test_concat_LIST_NUMBER() {
		assertEquals(list(1l, 2l, 3l, 4l, 5l, 6l), LIST_OF_NUMBER.concat(list(1l, 2l, 3l), list(4l, 5l, 6l)));
		assertEquals(list(10l, 20l, 30l, 40l, 50l, 60l), LIST_OF_NUMBER.concat(list(10l, 20l, 30l), list(40l, 50l, 60l)));
	}

	@Test
	public void test_remove_LIST_NUMBER() {
		assertEquals(list(1l, 2l, 5l, 6l), LIST_OF_NUMBER.remove(list(1l, 2l, 3l, 4l, 5l, 6l), list(3l, 4l)));
		assertEquals(list(), LIST_OF_NUMBER.remove(list(1l, 2l, 3l, 4l), list(1l, 2l, 3l, 4l)));
		assertEquals(list(1l, 2l, 3l, 4l, 5l, 6l), LIST_OF_NUMBER.remove(list(1l, 2l, 3l, 4l, 5l, 6l), list(7l, 8l, 9l)));
	}

	@Test
	public void test_convert_LIST_NUMBER() {
		assertEquals(list(125l, 225l), LIST_OF_NUMBER.convert("125,225"));
		assertEquals(list(125l), LIST_OF_NUMBER.convert(list(125l)));
		assertEquals(list(1l), LIST_OF_NUMBER.convert(list(1.25d)));
		assertEquals(list(2l), LIST_OF_NUMBER.convert(list(1.75d)));
		assertEquals(list(125l, 225l, 325l), LIST_OF_NUMBER.convert(list("125", "225", "325")));
		assertEquals(list(125l, 225l, 325l), LIST_OF_NUMBER.convert(set("125", "225", "325")));
		assertEquals(list(125l, 225l, 325l), LIST_OF_NUMBER.convert(list(125l, 225l, 325l)));
		assertEquals(list(125l, 225l, 325l), LIST_OF_NUMBER.convert(set(125l, 225l, 325l)));
		assertEquals(list(1l, 2l, 3l), LIST_OF_NUMBER.convert(list(1.25d, 2.25d, 3.25d)));
		assertEquals(list(1l, 2l, 3l), LIST_OF_NUMBER.convert(set(1.25d, 2.25d, 3.25d)));
		assertEquals(list(2l, 3l, 4l), LIST_OF_NUMBER.convert(list(1.75d, 2.75d, 3.75d)));
		assertEquals(list(2l, 3l, 4l), LIST_OF_NUMBER.convert(set(1.75d, 2.75d, 3.75d)));
	}
}
