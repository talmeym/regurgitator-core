package com.emarte.regurgitator.core;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class SubstituteValueTest {

	@Test
	public void testThis() {
		SubstituteValue toTest = new SubstituteValue("token", "nekot");
		assertEquals("1234nekot1234nekot1234nekot1234", toTest.process("1234token1234token1234token1234"));
	}
}
