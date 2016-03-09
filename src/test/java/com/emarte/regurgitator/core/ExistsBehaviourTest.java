package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static junit.framework.Assert.*;

public class ExistsBehaviourTest {
	private static ExistsBehaviour toTest = new ExistsBehaviour();

	@Test
	public void testExists() throws RegurgitatorException {
		assertTrue(toTest.evaluate(new Parameter(new ParameterPrototype("to-test", STRING, REPLACE), "value"), new Message(null), "true", false));
		assertTrue(toTest.evaluate(null, new Message(null), "false", true));
		assertFalse(toTest.evaluate(null, new Message(null), "true", false));
	}}
