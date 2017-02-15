package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertEquals;


public class FirstMatchOnwardsBehaviourTest {
	private static FirstMatchOnwardsBehaviour toTest = new FirstMatchOnwardsBehaviour();

	@Test
	public void testHappyPath() throws Exception {
		List evaluated = asList("2", "3");
		List all = asList("1", "2", "3", "4");
		List<Object> list = toTest.evaluate(evaluated, all, "4");
		assertEquals(asList("2", "3", "4"), list);
	}

	@Test
	public void testDefault() throws Exception {
		List all = asList("1", "2", "3", "4");
		List<Object> list = toTest.evaluate(new ArrayList<Object>(), all, "3");
		assertEquals(asList("3", "4"), list);
	}

	@Test(expected = RegurgitatorException.class)
	public void testNoDefault() throws Exception {
		List all = asList("1", "2", "3");
		toTest.evaluate(new ArrayList<Object>(), all, null);
	}
}