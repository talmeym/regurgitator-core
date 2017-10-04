package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.Arrays;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.*;
import static junit.framework.Assert.*;

public class EqualsBehaviourTest {
	private static EqualsBehaviour toTest = new EqualsBehaviour();

	@Test
	public void testthis() {
		ParameterPrototype stringPrototype = new ParameterPrototype("to-test", STRING, REPLACE);

		assertTrue(toTest.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "value", true));
		assertTrue(toTest.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "notvalue", false));
		assertFalse(toTest.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "notvalue", true));

		ParameterPrototype numberPrototype;
		numberPrototype = new ParameterPrototype("to-test", NUMBER, REPLACE);

		assertTrue(toTest.evaluate(new Parameter(numberPrototype, 5L), new Message(null), "5", true));
		assertTrue(toTest.evaluate(new Parameter(numberPrototype, 5L), new Message(null), "6", false));
		assertFalse(toTest.evaluate(new Parameter(numberPrototype, 5L), new Message(null), "6", true));

		ParameterPrototype decimalPrototype = new ParameterPrototype("to-test", DECIMAL, REPLACE);

		assertTrue(toTest.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.0", true));
		assertTrue(toTest.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.1", false));
		assertFalse(toTest.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.1", true));

		ParameterPrototype listStringPrototype = new ParameterPrototype("to-test", LIST_OF_STRING, REPLACE);

		assertTrue(toTest.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null),"one,two", true));
		assertTrue(toTest.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "one,three", false));
		assertFalse(toTest.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "one,three", true));		
	}
}
