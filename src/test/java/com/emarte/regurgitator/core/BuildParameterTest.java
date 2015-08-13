package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.type.DefaultTypes.STRING;
import static junit.framework.Assert.assertEquals;

public class BuildParameterTest {
	private static final String PARAM_NAME = "name";
	private static final ParameterType PARAM_TYPE = STRING;
	private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
	private static final String STEP_ID = "id";
	private static final String PARAM_CONTEXT = "context";
	private static final String BUILT_VALUE = "builtValue";

	private ParameterPrototype prototype = new ParameterPrototype(PARAM_NAME, PARAM_TYPE, PARAM_CONFLICT_POL);

	private ValueBuilder valueBuilder = new ValueBuilder() {
		@Override
		public Object build(Message parameters) throws RegurgitatorException {
			return BUILT_VALUE;
		}
	};

	private ValueValidator passingValueValidator = new ValueValidator() {
		@Override
		public boolean validate(Object value) throws RegurgitatorException {
			return true;
		}
	};

	private ValueValidator failingValueValidator = new ValueValidator() {
		@Override
		public boolean validate(Object value) throws RegurgitatorException {
			return false;
		}
	};

	private BuildParameter toTest = new BuildParameter(STEP_ID, prototype, PARAM_CONTEXT, valueBuilder, null);

	@Test
	public void testBasics() throws RegurgitatorException {
		assertEquals(STEP_ID, toTest.getId());
		Message message = new Message(null);
		toTest.execute(message);
		assertParametersContent(message);
	}

	private void assertParametersContent(Message message) {
		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(1, contextParameters.size());

		Parameter parameter = contextParameters.get(PARAM_NAME);
		assertEquals(PARAM_TYPE, parameter.getType());
		assertEquals(BUILT_VALUE, parameter.getValue());
	}

	@Test
	public void testValidatorPass() throws RegurgitatorException {
		toTest = new BuildParameter(STEP_ID, prototype, PARAM_CONTEXT, valueBuilder, passingValueValidator);
		Message message = new Message(null);
		toTest.execute(message);
		assertParametersContent(message);
	}

	@Test(expected = RegurgitatorException.class)
	public void testValidatorFail() throws RegurgitatorException {
		toTest = new BuildParameter(STEP_ID, prototype, PARAM_CONTEXT, valueBuilder, failingValueValidator);
		toTest.execute(new Message((null)));
	}

}
