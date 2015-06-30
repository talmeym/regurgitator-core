package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.ParameterType.DefaultImpl.DefaultImpl;
import static com.emarte.regurgitator.core.ValueGenerator.DefaultImpl.NUMBER;
import static junit.framework.Assert.assertEquals;

public class GenerateParameterTest {
	private static final String DEST_NAME = "newName";
	private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
	private static final String PARAM_CONTEXT = "context";
	private static final String SOURCE_ID = "sourceId";

	private ParameterPrototype destPrototype = new ParameterPrototype(DEST_NAME, DefaultImpl.NUMBER, PARAM_CONFLICT_POL);

	private GenerateParameter toTest = new GenerateParameter(SOURCE_ID, destPrototype, PARAM_CONTEXT, NUMBER);

	@Test
	public void testThis() throws RegurgitatorException {
		assertEquals(SOURCE_ID, toTest.getId());
		Message message = buildMessage();

		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(0, contextParameters.size());

		toTest.execute(message);

		assertEquals(1, contextParameters.size());
		Parameter parameter = contextParameters.get(DEST_NAME);
		assertEquals(DefaultImpl.NUMBER, parameter.getType());
	}

	private Message buildMessage() {
		return new Message(new ResponseCallBack() {
			@Override
			public void respond(Message message, Object value) {
			}
		});
	}
}
