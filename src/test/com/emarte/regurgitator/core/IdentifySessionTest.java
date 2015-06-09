package com.emarte.regurgitator.core;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class IdentifySessionTest {

	private static final String PARAM_VALUE = "value";
	private static final String CONTEXT = "context";
	private static final String PARAM_NAME = "name";

	private IdentifySession toTest = new IdentifySession("ID", new ContextLocation(CONTEXT, PARAM_NAME));

	@Test
	public void testHappyPath() throws RegurgitatorException {
		Message message = buildMessage();
		message.getContext(CONTEXT).add(new Parameter(new ParameterPrototype(PARAM_NAME, ParameterType.DefaultImpl.STRING, ConflictPolicy.REPLACE), PARAM_VALUE));

		toTest.execute(message);

		assertEquals(PARAM_VALUE, message.getSession().getId());
	}

	@Test(expected = IllegalStateException.class)
	public void testUnhappyPath() throws RegurgitatorException {
		Message message = buildMessage();
		message.getContext(CONTEXT).add(new Parameter(new ParameterPrototype("other_name", ParameterType.DefaultImpl.STRING, ConflictPolicy.REPLACE), PARAM_VALUE));

		toTest.execute(message);
	}

	private Message buildMessage() {
		return new Message(new ResponseCallBack() {
			@Override
			public void respond(Message message, Object value) {
			}
		});
	}
}
