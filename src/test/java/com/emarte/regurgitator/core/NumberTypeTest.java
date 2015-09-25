package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.NUMBER;
import static org.junit.Assert.assertEquals;

public class NumberTypeTest extends TypeTest {
	@Test
	public void test_concat_NUMBER() {
		assertEquals(12l, NUMBER.concat(8l, 4l));
		assertEquals(120l, NUMBER.concat(80l, 40l));
	}

	@Test(expected = ClassCastException.class)
	public void test_concat_invalid_prefix_NUMBER() {
		NUMBER.concat(8d, 4l);
	}

	@Test(expected = ClassCastException.class)
	public void test_concat_invalid_suffix_NUMBER() {
		NUMBER.concat(8l, 4d);
	}

	@Test
	public void test_remove_NUMBER() {
		assertEquals(8l, NUMBER.remove(12l, 4l));
		assertEquals(0l, NUMBER.remove(8l, 8l));
		assertEquals(-4l, NUMBER.remove(8l, 12l));
	}

	@Test(expected = ClassCastException.class)
	public void test_remove_invalid_existing_NUMBER() {
		NUMBER.remove(12.5d, 4l);
	}

	@Test(expected = ClassCastException.class)
	public void test_remove_invalid_new_NUMBER() {
		NUMBER.remove(12l, 4.5d);
	}

	@Test
	public void test_convert_NUMBER() {
		assertEquals(125l, NUMBER.convert("125"));
		assertEquals(125l, NUMBER.convert(125l));
		assertEquals(1l, NUMBER.convert(1.25d));
		assertEquals(2l, NUMBER.convert(1.75d));
		assertEquals(675l, NUMBER.convert(list("125", "225", "325")));
		assertEquals(675l, NUMBER.convert(set("125", "225", "325")));
		assertEquals(675l, NUMBER.convert(list(125l, 225l, 325l)));
		assertEquals(675l, NUMBER.convert(set(125l, 225l, 325l)));
		assertEquals(6l, NUMBER.convert(list(1.25d, 2.25d, 3.25d)));
		assertEquals(6l, NUMBER.convert(set(1.25d, 2.25d, 3.25d)));
		assertEquals(9l, NUMBER.convert(list(1.75d, 2.75d, 3.75d)));
		assertEquals(9l, NUMBER.convert(set(1.75d, 2.75d, 3.75d)));
	}
}
