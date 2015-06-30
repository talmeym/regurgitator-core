package com.emarte.regurgitator.core;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ExtractProcessorTest {
	@Test
	public void testThis() throws RegurgitatorException {
		ExtractProcessor toTest = new ExtractProcessor("This is {0} sentence", 0);
		assertEquals("a", toTest.process("This is a sentence"));
	}
}
