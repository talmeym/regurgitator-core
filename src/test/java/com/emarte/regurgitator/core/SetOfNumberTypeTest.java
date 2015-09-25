package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.SET_OF_NUMBER;
import static org.junit.Assert.assertEquals;

public class SetOfNumberTypeTest extends TypeTest {
	@Test
	public void test_concat_SET_NUMBER() {
		assertEquals(set(1l, 2l, 3l, 4l, 5l, 6l), SET_OF_NUMBER.concat(set(1l, 2l, 3l, 4l), set(3l, 4l, 5l, 6l)));
		assertEquals(set(10l, 20l, 30l, 40l, 50l, 60l), SET_OF_NUMBER.concat(set(10l, 20l, 30l, 40l), set(30l, 40l, 50l, 60l)));
	}

	@Test
	public void test_remove_SET_NUMBER() {
		assertEquals(set(1l, 2l, 5l, 6l), SET_OF_NUMBER.remove(set(1l, 2l, 3l, 4l, 5l, 6l), set(3l, 4l)));
		assertEquals(set(), SET_OF_NUMBER.remove(set(1l, 2l, 3l, 4l), set(1l, 2l, 3l, 4l)));
		assertEquals(set(1l, 2l, 3l, 4l, 5l, 6l), SET_OF_NUMBER.remove(set(1l, 2l, 3l, 4l, 5l, 6l), set(7l, 8l, 9l)));
	}

	@Test
	public void test_convert_SET_NUMBER() {
		assertEquals(set(125l, 225l), SET_OF_NUMBER.convert("125,225,125"));
		assertEquals(set(125l), SET_OF_NUMBER.convert(list(125l)));
		assertEquals(set(1l), SET_OF_NUMBER.convert(list(1.25d)));
		assertEquals(set(2l), SET_OF_NUMBER.convert(list(1.75d)));
		assertEquals(set(125l, 225l, 325l), SET_OF_NUMBER.convert(list("125", "225", "325", "125")));
		assertEquals(set(125l, 225l, 325l), SET_OF_NUMBER.convert(set("125", "225", "325")));
		assertEquals(set(125l, 225l, 325l), SET_OF_NUMBER.convert(list(125l, 225l, 325l)));
		assertEquals(set(125l, 225l, 325l), SET_OF_NUMBER.convert(set(125l, 225l, 325l)));
		assertEquals(set(1l, 2l, 3l), SET_OF_NUMBER.convert(list(1.25d, 2.25d, 3.25d, 1.25d)));
		assertEquals(set(1l, 2l, 3l), SET_OF_NUMBER.convert(set(1.25d, 2.25d, 3.25d)));
		assertEquals(set(2l, 3l, 4l), SET_OF_NUMBER.convert(list(1.75d, 2.75d, 3.75d, 1.75d)));
		assertEquals(set(2l, 3l, 4l), SET_OF_NUMBER.convert(set(1.75d, 2.75d, 3.75d)));
	}
}
