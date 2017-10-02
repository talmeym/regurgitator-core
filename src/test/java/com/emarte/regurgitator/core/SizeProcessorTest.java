package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class SizeProcessorTest {
	private SizeProcessor toTest = new SizeProcessor(false);
	private SizeProcessor toTestMinusOne = new SizeProcessor(true);

	@Test
	public void testThis() throws RegurgitatorException {
		assertEquals(3, toTest.process(Arrays.asList("one", "two", "three"), null));
		assertEquals(3, toTest.process(new HashSet<String>(Arrays.asList("one", "two", "three")), null));
	}

	@Test
	public void testMinusOne() throws RegurgitatorException {
		assertEquals(2, toTestMinusOne.process(Arrays.asList("one", "two", "three"), null));
		assertEquals(2, toTestMinusOne.process(new HashSet<String>(Arrays.asList("one", "two", "three")), null));
	}
}
