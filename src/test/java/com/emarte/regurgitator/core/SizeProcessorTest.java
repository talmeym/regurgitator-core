package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.*;

import static junit.framework.Assert.assertEquals;

public class SizeProcessorTest {
	private SizeProcessor toTest = new SizeProcessor();

	@Test
	public void testThis() throws RegurgitatorException {
		assertEquals(3, toTest.process(Arrays.asList("one", "two", "three"), null));
		assertEquals(3, toTest.process(new HashSet(Arrays.asList("one", "two", "three")), null));
	}
}
