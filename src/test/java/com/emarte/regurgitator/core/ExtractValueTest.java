package com.emarte.regurgitator.core;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class ExtractValueTest {
	@Test
	public void testThis() throws RegurgitatorException {
		ExtractValue toTest = new ExtractValue("This is {0} sentence", 0);
		assertEquals("a", toTest.process("This is a sentence"));
	}
}
