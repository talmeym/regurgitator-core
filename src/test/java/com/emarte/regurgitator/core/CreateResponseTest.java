package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.type.CoreTypes.STRING;
import static junit.framework.Assert.assertEquals;

public class CreateResponseTest {
	private static final String SOURCE_NAME = "name";
	private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
	private static final String PARAM_CONTEXT = "context";
	private static final String SOURCE_VALUE = "value";
	private static final ContextLocation SOURCE = new ContextLocation("context:name");
	private static final String STATIC_VALUE = "staticValue";
	private static final String PROCESSED_VALUE = "processedValue";
	private static final String SOURCE_ID = "sourceId";
	private static final String STATIC_ID = "staticId";
	private static final String PROCESSED_ID = "processedId";

	private ParameterPrototype sourcePrototype = new ParameterPrototype(SOURCE_NAME, STRING, PARAM_CONFLICT_POL);

	private CreateResponse sourceToTest = new CreateResponse(SOURCE_ID, SOURCE, null, null);
	private CreateResponse staticToTest = new CreateResponse(STATIC_ID, null, STATIC_VALUE, null);

	private ValueProcessor valueProcessor = new ValueProcessor() {
		@Override
		public Object process(Object value) throws RegurgitatorException {
			return PROCESSED_VALUE;
		}

	};

	private CreateResponse processorToTest = new CreateResponse(PROCESSED_ID, null, STATIC_VALUE, valueProcessor);

	private CollectingResponseCallBack callback = new CollectingResponseCallBack();

	@Test
	public void testSource() throws RegurgitatorException {
		assertEquals(SOURCE_ID, sourceToTest.getId());
		Message message = new Message(callback);

		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		contextParameters.add(new Parameter(sourcePrototype, SOURCE_VALUE));
		assertEquals(1, contextParameters.size());

		sourceToTest.execute(message);

		assertEquals(SOURCE_VALUE, callback.getValue());
	}

	@Test
	public void testStatic() throws RegurgitatorException {
		assertEquals(STATIC_ID, staticToTest.getId());
		Message message = new Message(callback);

		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(0, contextParameters.size());

		staticToTest.execute(message);

		assertEquals(STATIC_VALUE, callback.getValue());
	}

	@Test
	public void testProcessor() throws RegurgitatorException {
		assertEquals(PROCESSED_ID, processorToTest.getId());
		Message message = new Message(callback);

		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(0, contextParameters.size());

		processorToTest.execute(message);

		assertEquals(PROCESSED_VALUE, callback.getValue());
	}

	private class CollectingResponseCallBack implements ResponseCallBack {
		private Object value;

		@Override
		public void respond(Message message, Object value) {
			this.value = value;
		}

		public Object getValue() {
			return value;
		}
	};
}
