package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.*;

import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static org.junit.Assert.assertEquals;

public class StringTypeTest extends TypeTest {
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
}
