package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.type.CoreTypes.*;
import static junit.framework.Assert.assertEquals;

public class CreateParameterTest {
	private static final String SOURCE_NAME = "name";
	private static final String DEST_NAME = "newName";
	private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
	private static final String PARAM_CONTEXT = "context";
	private static final String SOURCE_VALUE = "value";
	private static final ContextLocation SOURCE = new ContextLocation("context:name");
	private static final String STATIC_VALUE = "staticValue";
	private static final String PROCESSED_VALUE = "processedValue";
	private static final String SOURCE_ID = "sourceId";
	private static final String STATIC_ID = "staticId";
	private static final String PROCESSED_ID = "processedId";
	private static final String CROSS_TYPE_ID = "crossTypeId";
	private static final String NUMBER_VALUE = "25";
	private static final long A_NUMBER = 25L;

	private ParameterPrototype sourcePrototype = new ParameterPrototype(SOURCE_NAME, STRING, PARAM_CONFLICT_POL);
	private ParameterPrototype destPrototype = new ParameterPrototype(DEST_NAME, STRING, PARAM_CONFLICT_POL);
	private ParameterPrototype crossTypePrototype = new ParameterPrototype(DEST_NAME, NUMBER, PARAM_CONFLICT_POL);

	private CreateParameter sourceToTest = new CreateParameter(SOURCE_ID, destPrototype, PARAM_CONTEXT, SOURCE, null, null);
	private CreateParameter staticToTest = new CreateParameter(STATIC_ID, destPrototype, PARAM_CONTEXT, null, STATIC_VALUE, null);

	private ValueProcessor valueProcessor = new ValueProcessor() {
		@Override
		public Object process(Object value) throws RegurgitatorException {
			return PROCESSED_VALUE;
		}

	};

	private CreateParameter processorToTest = new CreateParameter(PROCESSED_ID, destPrototype, PARAM_CONTEXT, null, STATIC_VALUE, valueProcessor);
	private CreateParameter crossTypeToTest = new CreateParameter(CROSS_TYPE_ID, crossTypePrototype, PARAM_CONTEXT, null, NUMBER_VALUE, null);

	@Test
	public void testSource() throws RegurgitatorException {
		assertEquals(SOURCE_ID, sourceToTest.getId());
		Message message = new Message(null);
		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		contextParameters.add(new Parameter(sourcePrototype, SOURCE_VALUE));
		assertEquals(1, contextParameters.size());
		sourceToTest.execute(message);
		assertParameter(contextParameters, DEST_NAME, 2, STRING, SOURCE_VALUE);
	}

	@Test
	public void testStatic() throws RegurgitatorException {
		assertEquals(STATIC_ID, staticToTest.getId());
		Message message = new Message(null);
		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(0, contextParameters.size());
		staticToTest.execute(message);
		assertParameter(contextParameters, DEST_NAME, 1, STRING, STATIC_VALUE);
	}

	@Test
	public void testProcessor() throws RegurgitatorException {
		assertEquals(PROCESSED_ID, processorToTest.getId());
		Message message = new Message(null);
		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(0, contextParameters.size());
		processorToTest.execute(message);
		assertParameter(contextParameters, DEST_NAME, 1, STRING, PROCESSED_VALUE);
	}

	@Test
	public void testCrossType() throws RegurgitatorException {
		assertEquals(CROSS_TYPE_ID, crossTypeToTest.getId());
		Message message = new Message(null);
		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(0, contextParameters.size());
		crossTypeToTest.execute(message);
		assertParameter(contextParameters, DEST_NAME, 1, NUMBER, A_NUMBER);
	}

	private void assertParameter(Parameters parameters, String name, int size, ParameterType type, Object value) {
		assertEquals(size, parameters.size());
		Parameter parameter = parameters.get(name);
		assertEquals(type, parameter.getType());
		assertEquals(value, parameter.getValue());
	}
}
