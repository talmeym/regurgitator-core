package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.SET_OF_DECIMAL;
import static org.junit.Assert.assertEquals;

public class SetOfDecimalTypeTest extends TypeTest {
	@Test
	public void test_concat_SET_DECIMAL() {
		assertEquals(set(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), SET_OF_DECIMAL.concat(set(1.5d, 2.5d, 3.5d, 4.5d), set(3.5d, 4.5d, 5.5d, 6.5d)));
		assertEquals(set(10.5d, 20.5d, 30.5d, 40.5d, 50.5d, 60.5d), SET_OF_DECIMAL.concat(set(10.5d, 20.5d, 30.5d, 40.5d), set(30.5d, 40.5d, 50.5d, 60.5d)));
	}

	@Test
	public void test_remove_SET_DECIMAL() {
		assertEquals(set(1.5d, 2.5d, 5.5d, 6.5d), SET_OF_DECIMAL.remove(set(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), set(3.5d, 4.5d)));
		assertEquals(set(), SET_OF_DECIMAL.remove(set(1.5d, 2.5d, 3.5d, 4.5d), set(1.5d, 2.5d, 3.5d, 4.5d)));
		assertEquals(set(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), SET_OF_DECIMAL.remove(set(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), set(7.5d, 8.5d, 9.5d)));
	}

	@Test
	public void test_convert_SET_DECIMAL() {
		assertEquals(set(1.25d, 2.25d), SET_OF_DECIMAL.convert("1.25,2.25,1.25"));
		assertEquals(set(125d), SET_OF_DECIMAL.convert(list(125l)));
		assertEquals(set(1.25d), SET_OF_DECIMAL.convert(list(1.25d)));
		assertEquals(set(1.25d, 2.25d, 3.25d), SET_OF_DECIMAL.convert(list("1.25", "2.25", "3.25", "1.25")));
		assertEquals(set(1.25d, 2.25d, 3.25d), SET_OF_DECIMAL.convert(set("1.25", "2.25", "3.25")));
		assertEquals(set(125d, 225d, 325d), SET_OF_DECIMAL.convert(list(125l, 225l, 325l)));
		assertEquals(set(125d, 225d, 325d), SET_OF_DECIMAL.convert(set(125l, 225l, 325l)));
		assertEquals(set(1.25d, 2.25d, 3.25d), SET_OF_DECIMAL.convert(list(1.25d, 2.25d, 3.25d, 1.25d)));
		assertEquals(set(1.25d, 2.25d, 3.25d), SET_OF_DECIMAL.convert(set(1.25d, 2.25d, 3.25d)));
	}
}
