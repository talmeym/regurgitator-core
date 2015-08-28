package com.emarte.regurgitator.core;

import com.emarte.regurgitator.core.behaviour.condition.*;
import org.junit.Test;

import java.util.Arrays;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.type.CoreTypes.*;
import static junit.framework.Assert.*;

public class ConditionBehaviourTest {
	private static ConditionBehaviour CONTAINS = new ContainsBehaviour();
	private static ConditionBehaviour EQUALS = new EqualsBehaviour();
	private static ConditionBehaviour EXISTS = new ExistsBehaviour();

	@Test
	public void testContains() throws RegurgitatorException {
		ContextLocation location = new ContextLocation("to-test");
		ParameterPrototype stringPrototype = new ParameterPrototype("to-test", STRING, REPLACE);

		assertTrue(CONTAINS.evaluate(location, buildMessage(new Parameter(stringPrototype, "value")), "lue", true));
		assertTrue(CONTAINS.evaluate(location, buildMessage(new Parameter(stringPrototype, "value")), "valve", false));
		assertFalse(CONTAINS.evaluate(location, buildMessage(new Parameter(stringPrototype, "value")), "valve", true));

		ParameterPrototype numberPrototype = new ParameterPrototype("to-test", NUMBER, REPLACE);

		assertTrue(CONTAINS.evaluate(location, buildMessage(new Parameter(numberPrototype, 5l)), "4", true));
		assertTrue(CONTAINS.evaluate(location, buildMessage(new Parameter(numberPrototype, 5l)), "6", false));
		assertFalse(CONTAINS.evaluate(location, buildMessage(new Parameter(numberPrototype, 5l)), "6", true));

		ParameterPrototype decimalPrototype = new ParameterPrototype("to-test", DECIMAL, REPLACE);

		assertTrue(CONTAINS.evaluate(location, buildMessage(new Parameter(decimalPrototype, 5.0d)), "4.9", true));
		assertTrue(CONTAINS.evaluate(location, buildMessage(new Parameter(decimalPrototype, 5.0d)), "5.1", false));
		assertFalse(CONTAINS.evaluate(location, buildMessage(new Parameter(decimalPrototype, 5.0d)), "5.1", true));

		ParameterPrototype listStringPrototype = new ParameterPrototype("to-test", LIST_OF_STRING, REPLACE);

		assertTrue(CONTAINS.evaluate(location, buildMessage(new Parameter(listStringPrototype, Arrays.asList("one", "two"))), "one,two", true));
		assertTrue(CONTAINS.evaluate(location, buildMessage(new Parameter(listStringPrototype, Arrays.asList("one", "two"))), "three", false));
		assertFalse(CONTAINS.evaluate(location, buildMessage(new Parameter(listStringPrototype, Arrays.asList("one", "two"))), "three", true));
	}

	private Message buildMessage(Parameter parameter) throws RegurgitatorException {
		Message message = new Message(null);
		message.getParameters().setValue(parameter);
		return message;
	}

	@Test
	public void testEquals() throws RegurgitatorException {
		ContextLocation location = new ContextLocation("to-test");
		ParameterPrototype stringPrototype = new ParameterPrototype("to-test", STRING, REPLACE);

		assertTrue(EQUALS.evaluate(location, buildMessage(new Parameter(stringPrototype, "value")), "value", true));
		assertTrue(EQUALS.evaluate(location, buildMessage(new Parameter(stringPrototype, "value")), "notvalue", false));
		assertFalse(EQUALS.evaluate(location, buildMessage(new Parameter(stringPrototype, "value")), "notvalue", true));

		ParameterPrototype numberPrototype;
		numberPrototype = new ParameterPrototype("to-test", NUMBER, REPLACE);

		assertTrue(EQUALS.evaluate(location, buildMessage(new Parameter(numberPrototype, 5l)), "5", true));
		assertTrue(EQUALS.evaluate(location, buildMessage(new Parameter(numberPrototype, 5l)), "6", false));
		assertFalse(EQUALS.evaluate(location, buildMessage(new Parameter(numberPrototype, 5l)), "6", true));

		ParameterPrototype decimalPrototype = new ParameterPrototype("to-test", DECIMAL, REPLACE);

		assertTrue(EQUALS.evaluate(location, buildMessage(new Parameter(decimalPrototype, 5.0d)), "5.0", true));
		assertTrue(EQUALS.evaluate(location, buildMessage(new Parameter(decimalPrototype, 5.0d)), "5.1", false));
		assertFalse(EQUALS.evaluate(location, buildMessage(new Parameter(decimalPrototype, 5.0d)), "5.1", true));

		ParameterPrototype listStringPrototype = new ParameterPrototype("to-test", LIST_OF_STRING, REPLACE);

		assertTrue(EQUALS.evaluate(location, buildMessage(new Parameter(listStringPrototype, Arrays.asList("one", "two"))),"one,two", true));
		assertTrue(EQUALS.evaluate(location, buildMessage(new Parameter(listStringPrototype, Arrays.asList("one", "two"))), "one,three", false));
		assertFalse(EQUALS.evaluate(location, buildMessage(new Parameter(listStringPrototype, Arrays.asList("one", "two"))), "one,three", true));
	}

	@Test
	public void testExists() throws RegurgitatorException {
		ContextLocation location = new ContextLocation("to-test");
		Parameter parameter = new Parameter(new ParameterPrototype("to-test", STRING, REPLACE), "value");

		assertTrue(EXISTS.evaluate(location, buildMessage(parameter), "true", false));
		assertTrue(EXISTS.evaluate(location, new Message(null), "false", true));
		assertFalse(EXISTS.evaluate(location, new Message(null), "true", false));
	}
}
