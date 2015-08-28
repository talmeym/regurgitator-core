package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.*;

import static com.emarte.regurgitator.core.type.CoreTypes.*;
import static org.junit.Assert.assertEquals;

public class ParameterTypeTest {
	@Test
	public void test_concat_STRING() {
		assertEquals("ABCDEF", STRING.concat("ABC", "DEF"));
		assertEquals("123456", STRING.concat("123", "456"));
	}

	@Test(expected = ClassCastException.class)
	public void test_concat_invalid_prefix_STRING(){
		STRING.concat(125l, "125");
	}

	@Test(expected = ClassCastException.class)
	public void test_concat_invalid_suffix_STRING(){
		STRING.concat("125", 125l);
	}

	@Test
	public void test_concat_LIST_STRING() {
		assertEquals(list("A", "B", "C", "D", "E", "F"), LIST_OF_STRING.concat(list("A", "B", "C"), list("D", "E", "F")));
		assertEquals(list("1", "2", "3", "4", "5", "6"), LIST_OF_STRING.concat(list("1", "2", "3"), list("4", "5", "6")));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_prefix_LIST_STRING() {
//		LIST_STRING.concat(list("A", 125l, "C"), list("D", "E", "F"));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_suffix_LIST_STRING() {
//		LIST_STRING.concat(list("A", "B", "C"), list("D", 125l, "F"));
//	}

	@Test
	public void test_concat_SET_STRING() {
		assertEquals(set("A", "B", "C", "D", "E", "F"), SET_OF_STRING.concat(set("A", "B", "C", "D"), set("C", "D", "E", "F")));
		assertEquals(set("1", "2", "3", "4", "5", "6"), SET_OF_STRING.concat(set("1", "2", "3", "4"), set("3", "4", "5", "6")));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_prefix_SET_STRING() {
//		SET_STRING.concat(set("A", 125l, "C"), set("D", "E", "F"));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_suffix_SET_STRING() {
//		SET_STRING.concat(set("A", "B", "C"), set("D", 125l, "F"));
//	}

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
	public void test_concat_LIST_NUMBER() {
		assertEquals(list(1l, 2l, 3l, 4l, 5l, 6l), LIST_OF_NUMBER.concat(list(1l, 2l, 3l), list(4l, 5l, 6l)));
		assertEquals(list(10l, 20l, 30l, 40l, 50l, 60l), LIST_OF_NUMBER.concat(list(10l, 20l, 30l), list(40l, 50l, 60l)));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_prefix_LIST_NUMBER() {
//		LIST_NUMBER.concat(list(1l, "2", 3l), list(4l, 6l, 7l));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_suffix_LIST_NUMBER() {
//		LIST_NUMBER.concat(list(1l, 2l, 3l), list(4l, "6", 7l));
//	}

	@Test
	public void test_concat_SET_NUMBER() {
		assertEquals(set(1l, 2l, 3l, 4l, 5l, 6l), SET_OF_NUMBER.concat(set(1l, 2l, 3l, 4l), set(3l, 4l, 5l, 6l)));
		assertEquals(set(10l, 20l, 30l, 40l, 50l, 60l), SET_OF_NUMBER.concat(set(10l, 20l, 30l, 40l), set(30l, 40l, 50l, 60l)));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_prefix_SET_NUMBER() {
//		SET_NUMBER.concat(set(1l, "2", 3l), set(4l, 6l, 7l));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_suffix_SET_NUMBER() {
//		SET_NUMBER.concat(set(1l, 2l, 3l), set(4l, "6", 7l));
//	}

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
	public void test_concat_LIST_DECIMAL() {
		assertEquals(list(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), LIST_OF_DECIMAL.concat(list(1.5d, 2.5d, 3.5d), list(4.5d, 5.5d, 6.5d)));
		assertEquals(list(10.5d, 20.5d, 30.5d, 40.5d, 50.5d, 60.5d), LIST_OF_DECIMAL.concat(list(10.5d, 20.5d, 30.5d), list(40.5d, 50.5d, 60.5d)));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_prefix_LIST_DECIMAL() {
//		LIST_DECIMAL.concat(list(1.5d, "2.5", 3.5d), list(4.5d, 6.5d, 7.5d));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_suffix_LIST_DECIMAL() {
//		LIST_DECIMAL.concat(list(1.5d, 2.5d, 3.5d), list(4.5d, "6.5", 7.5d));
//	}

	@Test
	public void test_concat_SET_DECIMAL() {
		assertEquals(set(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), SET_OF_DECIMAL.concat(set(1.5d, 2.5d, 3.5d, 4.5d), set(3.5d, 4.5d, 5.5d, 6.5d)));
		assertEquals(set(10.5d, 20.5d, 30.5d, 40.5d, 50.5d, 60.5d), SET_OF_DECIMAL.concat(set(10.5d, 20.5d, 30.5d, 40.5d), set(30.5d, 40.5d, 50.5d, 60.5d)));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_prefix_SET_DECIMAL() {
//		SET_DECIMAL.concat(set(1.5d, "2.5", 3.5d), set(4.5d, 6.5d, 7.5d));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_concat_invalid_suffix_SET_DECIMAL() {
//		SET_DECIMAL.concat(set(1.5d, 2.5d, 3.5d), set(4.5d, "6.5", 7.5d));
//	}

	@Test
	public void test_remove_STRING() {
		assertEquals("ABEF", STRING.remove("ABCDEF", "CD"));
		assertEquals("", STRING.remove("ABCD", "ABCD"));
		assertEquals("ABCDEF", STRING.remove("ABCDEF", "GHI"));
	}

	@Test(expected = ClassCastException.class)
	public void test_remove_invalid_existing_STRING() {
		STRING.remove(345l, "ABC");
	}

	@Test(expected = ClassCastException.class)
	public void test_remove_invalid_new_STRING() {
		STRING.remove("ABC", 345l);
	}

	@Test
	public void test_remove_LIST_STRING() {
		assertEquals(list("A", "B", "E", "F"), LIST_OF_STRING.remove(list("A", "B", "C", "D", "E", "F"), list("C", "D")));
		assertEquals(list(), LIST_OF_STRING.remove(list("A", "B", "C", "D"), list("A", "B", "C", "D")));
		assertEquals(list("A", "B", "C", "D", "E", "F"), LIST_OF_STRING.remove(list("A", "B", "C", "D", "E", "F"), list("G", "H", "I")));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_remove_invalid_existing_LIST_STRING() {
//		LIST_STRING.remove(list("A", "B", 345l, "D"), list("A", "B", "C"));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_remove_invalid_new_LIST_STRING() {
//		LIST_STRING.remove(list("A", "B", "C", "D"), list("A", 345l, "C"));
//	}

	@Test
	public void test_remove_SET_STRING() {
		assertEquals(set("A", "B", "E", "F"), SET_OF_STRING.remove(set("A", "B", "C", "D", "E", "F"), set("C", "D")));
		assertEquals(set(), SET_OF_STRING.remove(set("A", "B", "C", "D"), set("A", "B", "C", "D")));
		assertEquals(set("A", "B", "C", "D", "E", "F"), SET_OF_STRING.remove(set("A", "B", "C", "D", "E", "F"), set("G", "H", "I")));
	}

//	@Test(expected = IllegalArgumentException.class)
//	public void test_remove_invalid_existing_SET_STRING() {
//		SET_STRING.remove(set("A", "B", 345l, "D"), set("A", "B", "C"));
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void test_remove_invalid_new_SET_STRING() {
//		SET_STRING.remove(set("A", "B", "C", "D"), set("A", 345l, "C"));
//	}

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
	public void test_remove_LIST_NUMBER() {
		assertEquals(list(1l, 2l, 5l, 6l), LIST_OF_NUMBER.remove(list(1l, 2l, 3l, 4l, 5l, 6l), list(3l, 4l)));
		assertEquals(list(), LIST_OF_NUMBER.remove(list(1l, 2l, 3l, 4l), list(1l, 2l, 3l, 4l)));
		assertEquals(list(1l, 2l, 3l, 4l, 5l, 6l), LIST_OF_NUMBER.remove(list(1l, 2l, 3l, 4l, 5l, 6l), list(7l, 8l, 9l)));
	}

//	@Test(expected = IllegalArgumentException.class)
//	public void test_remove_invalid_existing_LIST_NUMBER() {
//		LIST_NUMBER.remove(list(125l, 225l, "325", 425l), list(125l, 225l, 325l));
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void test_remove_invalid_new_LIST_NUMBER() {
//		LIST_NUMBER.remove(list(125l, 225l, 325l, 425l), list(125l, "225", 325l));
//	}

	@Test
	public void test_remove_SET_NUMBER() {
		assertEquals(set(1l, 2l, 5l, 6l), SET_OF_NUMBER.remove(set(1l, 2l, 3l, 4l, 5l, 6l), set(3l, 4l)));
		assertEquals(set(), SET_OF_NUMBER.remove(set(1l, 2l, 3l, 4l), set(1l, 2l, 3l, 4l)));
		assertEquals(set(1l, 2l, 3l, 4l, 5l, 6l), SET_OF_NUMBER.remove(set(1l, 2l, 3l, 4l, 5l, 6l), set(7l, 8l, 9l)));
	}

//	@Test(expected = IllegalArgumentException.class)
//	public void test_remove_invalid_existing_SET_NUMBER() {
//		SET_NUMBER.remove(set(125l, 225l, "325", 425l), set(125l, 225l, 325l));
//	}
//
//	@Test(expected = IllegalArgumentException.class)
//	public void test_remove_invalid_new_SET_NUMBER() {
//		SET_NUMBER.remove(set(125l, 225l, 325l, 425l), set(125l, "225", 325l));
//	}

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
	public void test_remove_LIST_DECIMAL() {
		assertEquals(list(1.5d, 2.5d, 5.5d, 6.5d), LIST_OF_DECIMAL.remove(list(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), list(3.5d, 4.5d)));
		assertEquals(list(), LIST_OF_DECIMAL.remove(list(1.5d, 2.5d, 3.5d, 4.5d), list(1.5d, 2.5d, 3.5d, 4.5d)));
		assertEquals(list(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), LIST_OF_DECIMAL.remove(list(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), list(7.5d, 8.5d, 9.5d)));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_remove_invalid_existing_LIST_DECIMAL() {
//		LIST_DECIMAL.remove(list(1.25d, 2.25d, "325", 4.25d), list(1.25d, 2.25d, 3.25d));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_remove_invalid_new_LIST_DECIMAL() {
//		LIST_DECIMAL.remove(list(1.25d, 2.25d, 3.25d, 4.25d), list(1.25d, "225", 3.25d));
//	}

	@Test
	public void test_remove_SET_DECIMAL() {
		assertEquals(set(1.5d, 2.5d, 5.5d, 6.5d), SET_OF_DECIMAL.remove(set(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), set(3.5d, 4.5d)));
		assertEquals(set(), SET_OF_DECIMAL.remove(set(1.5d, 2.5d, 3.5d, 4.5d), set(1.5d, 2.5d, 3.5d, 4.5d)));
		assertEquals(set(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), SET_OF_DECIMAL.remove(set(1.5d, 2.5d, 3.5d, 4.5d, 5.5d, 6.5d), set(7.5d, 8.5d, 9.5d)));
	}

//	@Test(expected = ClassCastException.class)
//	public void test_remove_invalid_existing_SET_DECIMAL() {
//		SET_DECIMAL.remove(set(1.25d, 2.25d, "325", 4.25d), set(1.25d, 2.25d, 3.25d));
//	}
//
//	@Test(expected = ClassCastException.class)
//	public void test_remove_invalid_new_SET_DECIMAL() {
//		SET_DECIMAL.remove(set(1.25d, 2.25d, 3.25d, 4.25d), set(1.25d, "225", 3.25d));
//	}

	@Test
	public void test_convert_STRING() {
		assertEquals("ABC", STRING.convert("ABC"));
		assertEquals("125", STRING.convert(125l));
		assertEquals("1.25", STRING.convert(1.25d));
		assertEquals("ABC,DEF", STRING.convert(list("ABC", "DEF")));
		assertEquals("ABC,DEF,GHI", STRING.convert(set("ABC", "DEF", "GHI")));
		assertEquals("125,225", STRING.convert(list(125l, 225l)));
		assertEquals("125,225,325", STRING.convert(set(125l, 225l, 325l)));
		assertEquals("1.25,2.25", STRING.convert(list(1.25d, 2.25d)));
		assertEquals("1.25,2.25,3.25", STRING.convert(set(1.25d, 2.25d, 3.25d)));
	}

	@Test
	public void test_convert_LIST_STRING() {
		assertEquals(list("ABC", "DEF", "GHI"), LIST_OF_STRING.convert(list("ABC", "DEF", "GHI")));
		assertEquals(list("ABC", "DEF", "GHI"), LIST_OF_STRING.convert("ABC,DEF,GHI"));
		assertEquals(list("125"), LIST_OF_STRING.convert(125l));
		assertEquals(list("1.25"), LIST_OF_STRING.convert(1.25d));
		assertEquals(list("125", "225"), LIST_OF_STRING.convert(list(125l, 225l)));
		assertEquals(list("1.25", "2.25", "3.25"), LIST_OF_STRING.convert(list(1.25d, 2.25d, 3.25d)));
		assertEquals(list("125", "225"), LIST_OF_STRING.convert(set(125l, 225l)));
		assertEquals(list("1.25", "2.25", "3.25"), LIST_OF_STRING.convert(set(1.25d, 2.25d, 3.25d)));
	}

	@Test
	public void test_convert_SET_STRING() {
		assertEquals(set("ABC", "DEF", "GHI"), SET_OF_STRING.convert(set("ABC", "DEF", "GHI")));
		assertEquals(set("ABC", "DEF", "GHI"), SET_OF_STRING.convert("ABC,DEF,GHI,ABC"));
		assertEquals(set("125"), SET_OF_STRING.convert(125l));
		assertEquals(set("1.25"), SET_OF_STRING.convert(1.25d));
		assertEquals(set("125", "225"), SET_OF_STRING.convert(list(125l, 225l, 125l)));
		assertEquals(set("1.25", "2.25", "3.25"), SET_OF_STRING.convert(list(1.25d, 2.25d, 3.25d, 1.25d)));
		assertEquals(set("125", "225"), SET_OF_STRING.convert(set(125l, 225l)));
		assertEquals(set("1.25", "2.25", "3.25"), SET_OF_STRING.convert(set(1.25d, 2.25d, 3.25d)));
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

	@Test
	public void test_convert_LIST_DECIMAL() {
		assertEquals(list(1.25d, 2.25d), LIST_OF_DECIMAL.convert("1.25,2.25"));
		assertEquals(list(125d), LIST_OF_DECIMAL.convert(list(125l)));
		assertEquals(list(1.25d), LIST_OF_DECIMAL.convert(list(1.25d)));
		assertEquals(list(1.25d, 2.25d, 3.25d), LIST_OF_DECIMAL.convert(list("1.25", "2.25", "3.25")));
		assertEquals(list(1.25d, 2.25d, 3.25d), LIST_OF_DECIMAL.convert(set("1.25", "2.25", "3.25")));
		assertEquals(list(125d, 225d, 325d), LIST_OF_DECIMAL.convert(list(125l, 225l, 325l)));
		assertEquals(list(125d, 225d, 325d), LIST_OF_DECIMAL.convert(set(125l, 225l, 325l)));
		assertEquals(list(1.25d, 2.25d, 3.25d), LIST_OF_DECIMAL.convert(list(1.25d, 2.25d, 3.25d)));
		assertEquals(list(1.25d, 2.25d, 3.25d), LIST_OF_DECIMAL.convert(set(1.25d, 2.25d, 3.25d)));
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

	private Set set(Object... things) {
		return new LinkedHashSet(Arrays.asList(things));
	}

	private List list(Object... things) {
		return Arrays.asList(things);
	}
}
