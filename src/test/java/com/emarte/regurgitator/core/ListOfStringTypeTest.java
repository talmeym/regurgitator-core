package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.CoreTypes.LIST_OF_STRING;
import static org.junit.Assert.assertEquals;

public class ListOfStringTypeTest extends TypeTest {
	@Test
	public void test_concat_LIST_STRING() {
		assertEquals(list("A", "B", "C", "D", "E", "F"), LIST_OF_STRING.concat(list("A", "B", "C"), list("D", "E", "F")));
		assertEquals(list("1", "2", "3", "4", "5", "6"), LIST_OF_STRING.concat(list("1", "2", "3"), list("4", "5", "6")));
	}

	@Test
	public void test_remove_LIST_STRING() {
		assertEquals(list("A", "B", "E", "F"), LIST_OF_STRING.remove(list("A", "B", "C", "D", "E", "F"), list("C", "D")));
		assertEquals(list(), LIST_OF_STRING.remove(list("A", "B", "C", "D"), list("A", "B", "C", "D")));
		assertEquals(list("A", "B", "C", "D", "E", "F"), LIST_OF_STRING.remove(list("A", "B", "C", "D", "E", "F"), list("G", "H", "I")));
	}

	@Test
	public void test_convert_LIST_STRING() {
		assertEquals(list("ABC", "DEF", "GHI"), LIST_OF_STRING.convert(list("ABC", "DEF", "GHI")));
		assertEquals(list("ABC", "DEF", "GHI"), LIST_OF_STRING.convert("ABC,DEF,GHI"));
		assertEquals(list("125"), LIST_OF_STRING.convert(125L));
		assertEquals(list("1.25"), LIST_OF_STRING.convert(1.25d));
		assertEquals(list("125", "225"), LIST_OF_STRING.convert(list(125L, 225L)));
		assertEquals(list("1.25", "2.25", "3.25"), LIST_OF_STRING.convert(list(1.25d, 2.25d, 3.25d)));
		assertEquals(list("125", "225"), LIST_OF_STRING.convert(set(125L, 225L)));
		assertEquals(list("1.25", "2.25", "3.25"), LIST_OF_STRING.convert(set(1.25d, 2.25d, 3.25d)));
	}
}
