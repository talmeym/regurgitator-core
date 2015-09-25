package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.DECIMAL;
import static org.junit.Assert.assertEquals;

public class DecimalTypeTest extends TypeTest {
	@Test
	public void test_concat_DECIMAL() {
		assertEquals(12d, DECIMAL.concat(7.5d, 4.5d));
		assertEquals(111d, DECIMAL.concat(70.5d, 40.5d));
	}

	@Test(expected = ClassCastException.class)
	public void test_concat_invalid_prefix_DECIMAL() {
		DECIMAL.concat(7l, 4.5d);
	}

	@Test(expected = ClassCastException.class)
	public void test_concat_invalid_suffix_DECIMAL() {
		DECIMAL.concat(7.5d, 4l);
	}

	@Test
	public void test_remove_DECIMAL() {
		assertEquals(8.0d, DECIMAL.remove(12.5d, 4.5d));
		assertEquals(0d, DECIMAL.remove(8.5d, 8.5d));
		assertEquals(-4.0d, DECIMAL.remove(8.5d, 12.5d));
	}

	@Test(expected = ClassCastException.class)
	public void test_remove_invalid_existing_DECIMAL() {
		DECIMAL.remove(12l, 4.5d);
	}

	@Test(expected = ClassCastException.class)
	public void test_remove_invalid_new_DECIMAL() {
		DECIMAL.remove(12.5d, 4l);
	}

	@Test
	public void test_convert_DECIMAL() {
		assertEquals(1.25d, DECIMAL.convert("1.25"));
		assertEquals(125d, DECIMAL.convert(125l));
		assertEquals(1.25d, DECIMAL.convert(1.25d));
		assertEquals(6.75d, DECIMAL.convert(list("1.25", "2.25", "3.25")));
		assertEquals(6.75d, DECIMAL.convert(set("1.25", "2.25", "3.25")));
		assertEquals(6.75d, DECIMAL.convert(list(1.25d, 2.25d, 3.25d)));
		assertEquals(6.75d, DECIMAL.convert(set(1.25d, 2.25d, 3.25d)));
		assertEquals(675d, DECIMAL.convert(list(125l, 225l, 325l)));
		assertEquals(675d, DECIMAL.convert(set(125l, 225l, 325l)));
	}
}
