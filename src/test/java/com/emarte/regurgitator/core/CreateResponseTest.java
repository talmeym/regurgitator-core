package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.ParameterType.DefaultImpl.*;
import static junit.framework.Assert.assertEquals;

public class CreateResponseTest {
	private static final String SOURCE_NAME = "name";
	private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
	private static final String PARAM_CONTEXT = "context";
	private static final String SOURCE_VALUE = "value";
	private static final ContextLocation SOURCE = new ContextLocation("context:name");
	private static final String STATIC_VALUE = "staticValue";
	private static final String SOURCE_ID = "sourceId";
	private static final String STATIC_ID = "staticId";

	private ParameterPrototype sourcePrototype = new ParameterPrototype(SOURCE_NAME, STRING, PARAM_CONFLICT_POL);

	private CreateResponse sourceToTest = new CreateResponse(SOURCE_ID, SOURCE, null);
	private CreateResponse staticToTest = new CreateResponse(STATIC_ID, null, STATIC_VALUE);

	private CollectingResponseCallBack callback = new CollectingResponseCallBack();

	@Test
	public void testSource() throws RegurgitatorException {
		assertEquals(SOURCE_ID, sourceToTest.getId());
		Message message = buildMessage();

		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		contextParameters.add(new Parameter(sourcePrototype, SOURCE_VALUE));
		assertEquals(1, contextParameters.size());

		sourceToTest.execute(message);

		assertEquals(SOURCE_VALUE, callback.getValue());
	}

	@Test
	public void testStatic() throws RegurgitatorException {
		assertEquals(STATIC_ID, staticToTest.getId());
		Message message = buildMessage();

		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(0, contextParameters.size());

		staticToTest.execute(message);

		assertEquals(STATIC_VALUE, callback.getValue());
	}

	private Message buildMessage() {
		return new Message(callback);
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
