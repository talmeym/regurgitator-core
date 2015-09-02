package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static junit.framework.Assert.assertEquals;

public class IdentifySessionTest {

	private static final String PARAM_VALUE = "value";
	private static final String CONTEXT = "context";
	private static final String PARAM_NAME = "name";

	private IdentifySession toTest = new IdentifySession("ID", new ContextLocation(CONTEXT, PARAM_NAME));

	@Test
	public void testHappyPath() throws RegurgitatorException {
		Message message = new Message(null);
		message.getContext(CONTEXT).add(new Parameter(new ParameterPrototype(PARAM_NAME, STRING, REPLACE), PARAM_VALUE));

		toTest.execute(message);

		assertEquals(PARAM_VALUE, message.getSession().getId());
	}

	@Test(expected = IllegalStateException.class)
	public void testUnhappyPath() throws RegurgitatorException {
		Message message = new Message(null);
		message.getContext(CONTEXT).add(new Parameter(new ParameterPrototype("other_name", STRING, REPLACE), PARAM_VALUE));

		toTest.execute(message);
	}
}
