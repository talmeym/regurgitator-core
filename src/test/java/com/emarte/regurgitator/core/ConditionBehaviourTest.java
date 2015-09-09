package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.Arrays;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.*;
import static junit.framework.Assert.*;

public class ConditionBehaviourTest {
	private static ConditionBehaviour CONTAINS = new ContainsBehaviour();
	private static ConditionBehaviour EQUALS = new EqualsBehaviour();
	private static ConditionBehaviour EXISTS = new ExistsBehaviour();

	@Test
	public void testContains() throws RegurgitatorException {
		ParameterPrototype stringPrototype = new ParameterPrototype("to-test", STRING, REPLACE);

		assertTrue(CONTAINS.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "lue", true));
		assertTrue(CONTAINS.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "valve", false));
		assertFalse(CONTAINS.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "valve", true));

		ParameterPrototype numberPrototype = new ParameterPrototype("to-test", NUMBER, REPLACE);

		assertTrue(CONTAINS.evaluate(new Parameter(numberPrototype, 5l), new Message(null), "4", true));
		assertTrue(CONTAINS.evaluate(new Parameter(numberPrototype, 5l), new Message(null), "6", false));
		assertFalse(CONTAINS.evaluate(new Parameter(numberPrototype, 5l), new Message(null), "6", true));

		ParameterPrototype decimalPrototype = new ParameterPrototype("to-test", DECIMAL, REPLACE);

		assertTrue(CONTAINS.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "4.9", true));
		assertTrue(CONTAINS.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.1", false));
		assertFalse(CONTAINS.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.1", true));

		ParameterPrototype listStringPrototype = new ParameterPrototype("to-test", LIST_OF_STRING, REPLACE);

		assertTrue(CONTAINS.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "one,two", true));
		assertTrue(CONTAINS.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "three", false));
		assertFalse(CONTAINS.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "three", true));
	}

	@Test
	public void testEquals() throws RegurgitatorException {
		ParameterPrototype stringPrototype = new ParameterPrototype("to-test", STRING, REPLACE);

		assertTrue(EQUALS.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "value", true));
		assertTrue(EQUALS.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "notvalue", false));
		assertFalse(EQUALS.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "notvalue", true));

		ParameterPrototype numberPrototype;
		numberPrototype = new ParameterPrototype("to-test", NUMBER, REPLACE);

		assertTrue(EQUALS.evaluate(new Parameter(numberPrototype, 5l), new Message(null), "5", true));
		assertTrue(EQUALS.evaluate(new Parameter(numberPrototype, 5l), new Message(null), "6", false));
		assertFalse(EQUALS.evaluate(new Parameter(numberPrototype, 5l), new Message(null), "6", true));

		ParameterPrototype decimalPrototype = new ParameterPrototype("to-test", DECIMAL, REPLACE);

		assertTrue(EQUALS.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.0", true));
		assertTrue(EQUALS.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.1", false));
		assertFalse(EQUALS.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.1", true));

		ParameterPrototype listStringPrototype = new ParameterPrototype("to-test", LIST_OF_STRING, REPLACE);

		assertTrue(EQUALS.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null),"one,two", true));
		assertTrue(EQUALS.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "one,three", false));
		assertFalse(EQUALS.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "one,three", true));
	}

	@Test
	public void testExists() throws RegurgitatorException {
		assertTrue(EXISTS.evaluate(new Parameter(new ParameterPrototype("to-test", STRING, REPLACE), "value"), new Message(null), "true", false));
		assertTrue(EXISTS.evaluate(null, new Message(null), "false", true));
		assertFalse(EXISTS.evaluate(null, new Message(null), "true", false));
	}
}
