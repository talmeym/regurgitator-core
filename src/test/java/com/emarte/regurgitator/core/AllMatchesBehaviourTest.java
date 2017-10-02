package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static junit.framework.Assert.assertEquals;

public class AllMatchesBehaviourTest {
	private static AllMatchesBehaviour toTest = new AllMatchesBehaviour();

	@Test
	public void testHappyPath() {
		List evaluated = asList("1", "3");
		List all = asList("1", "2", "3");
		List<Object> list = toTest.evaluate(evaluated, all, "4");
		assertEquals(asList("1", "3"), list);
	}

	@Test
	public void testDefault() {
		List all = asList("1", "2", "3");
		List<Object> list = toTest.evaluate(new ArrayList<Object>(), all, "4");
		assertEquals(singletonList("4"), list);
	}

	@Test(expected = IllegalStateException.class)
	public void testNoDefault() {
		List all = asList("1", "2", "3");
		toTest.evaluate(new ArrayList<Object>(), all, null);
	}
}
