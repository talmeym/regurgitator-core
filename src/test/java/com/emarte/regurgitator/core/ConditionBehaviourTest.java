package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.Arrays;

import static com.emarte.regurgitator.core.ConditionBehaviour.DefaultImpl.*;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class ConditionBehaviourTest {

	@Test
	public void testContains() throws RegurgitatorException {
		ContextLocation location = new ContextLocation("context:location");
		ParameterPrototype stringPrototype = new ParameterPrototype("to-test", ParameterType.DefaultImpl.STRING, ConflictPolicy.REPLACE);

		assertTrue(CONTAINS.evaluate(location, new Parameter(stringPrototype, "value"), "lue", true));
		assertTrue(CONTAINS.evaluate(location, new Parameter(stringPrototype, "value"), "valve", false));
		assertFalse(CONTAINS.evaluate(location, new Parameter(stringPrototype, "value"), "valve", true));

		ParameterPrototype numberPrototype = new ParameterPrototype("to-test", ParameterType.DefaultImpl.NUMBER, ConflictPolicy.REPLACE);

		assertTrue(CONTAINS.evaluate(location, new Parameter(numberPrototype, 5l), "4", true));
		assertTrue(CONTAINS.evaluate(location, new Parameter(numberPrototype, 5l), "6", false));
		assertFalse(CONTAINS.evaluate(location, new Parameter(numberPrototype, 5l), "6", true));

		ParameterPrototype decimalPrototype = new ParameterPrototype("to-test", ParameterType.DefaultImpl.DECIMAL, ConflictPolicy.REPLACE);

		assertTrue(CONTAINS.evaluate(location, new Parameter(decimalPrototype, 5.0d), "4.9", true));
		assertTrue(CONTAINS.evaluate(location, new Parameter(decimalPrototype, 5.0d), "5.1", false));
		assertFalse(CONTAINS.evaluate(location, new Parameter(decimalPrototype, 5.0d), "5.1", true));

		ParameterPrototype listStringPrototype = new ParameterPrototype("to-test", ParameterType.DefaultImpl.LIST_STRING, ConflictPolicy.REPLACE);

		assertTrue(CONTAINS.evaluate(location, new Parameter(listStringPrototype, Arrays.asList("one", "two")), "one,two", true));
		assertTrue(CONTAINS.evaluate(location, new Parameter(listStringPrototype, Arrays.asList("one", "two")), "three", false));
		assertFalse(CONTAINS.evaluate(location, new Parameter(listStringPrototype, Arrays.asList("one", "two")), "three", true));
	}

	@Test
	public void testEquals() throws RegurgitatorException {
		ContextLocation location = new ContextLocation("context:location");
		ParameterPrototype stringPrototype = new ParameterPrototype("to-test", ParameterType.DefaultImpl.STRING, ConflictPolicy.REPLACE);

		assertTrue(EQUALS.evaluate(location, new Parameter(stringPrototype, "value"), "value", true));
		assertTrue(EQUALS.evaluate(location, new Parameter(stringPrototype, "value"), "notvalue", false));
		assertFalse(EQUALS.evaluate(location, new Parameter(stringPrototype, "value"), "notvalue", true));

		ParameterPrototype numberPrototype = new ParameterPrototype("to-test", ParameterType.DefaultImpl.NUMBER, ConflictPolicy.REPLACE);

		assertTrue(EQUALS.evaluate(location, new Parameter(numberPrototype, 5l), "5", true));
		assertTrue(EQUALS.evaluate(location, new Parameter(numberPrototype, 5l), "6", false));
		assertFalse(EQUALS.evaluate(location, new Parameter(numberPrototype, 5l), "6", true));

		ParameterPrototype decimalPrototype = new ParameterPrototype("to-test", ParameterType.DefaultImpl.DECIMAL, ConflictPolicy.REPLACE);

		assertTrue(EQUALS.evaluate(location, new Parameter(decimalPrototype, 5.0d), "5.0", true));
		assertTrue(EQUALS.evaluate(location, new Parameter(decimalPrototype, 5.0d), "5.1", false));
		assertFalse(EQUALS.evaluate(location, new Parameter(decimalPrototype, 5.0d), "5.1", true));

		ParameterPrototype listStringPrototype = new ParameterPrototype("to-test", ParameterType.DefaultImpl.LIST_STRING, ConflictPolicy.REPLACE);

		assertTrue(EQUALS.evaluate(location, new Parameter(listStringPrototype, Arrays.asList("one", "two")),"one,two", true));
		assertTrue(EQUALS.evaluate(location, new Parameter(listStringPrototype, Arrays.asList("one", "two")), "one,three", false));
		assertFalse(EQUALS.evaluate(location, new Parameter(listStringPrototype, Arrays.asList("one", "two")), "one,three", true));
	}

	@Test
	public void testExists() throws RegurgitatorException {
		ContextLocation location = new ContextLocation("context:location");
		Parameter parameter = new Parameter(new ParameterPrototype("to-test", ParameterType.DefaultImpl.STRING, ConflictPolicy.REPLACE), "value");

		assertTrue(EXISTS.evaluate(location, parameter, "true", false));
		assertTrue(EXISTS.evaluate(location, null, "false", true));
		assertFalse(EXISTS.evaluate(location, null, "true", false));
	}
}
