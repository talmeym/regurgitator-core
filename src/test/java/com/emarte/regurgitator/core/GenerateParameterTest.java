package com.emarte.regurgitator.core;

import com.emarte.regurgitator.core.generator.NumberGenerator;
import com.emarte.regurgitator.core.type.DefaultTypes;
import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static junit.framework.Assert.assertEquals;

public class GenerateParameterTest {
	private static final String DEST_NAME = "newName";
	private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
	private static final String PARAM_CONTEXT = "context";
	private static final String SOURCE_ID = "sourceId";

	private ParameterPrototype destPrototype = new ParameterPrototype(DEST_NAME, DefaultTypes.NUMBER, PARAM_CONFLICT_POL);

	private static ValueGenerator NUMBER = new NumberGenerator();

	private GenerateParameter toTest = new GenerateParameter(SOURCE_ID, destPrototype, PARAM_CONTEXT, NUMBER);

	@Test
	public void testThis() throws RegurgitatorException {
		assertEquals(SOURCE_ID, toTest.getId());
		Message message = new Message(null);

		Parameters contextParameters = message.getContext(PARAM_CONTEXT);
		assertEquals(0, contextParameters.size());

		toTest.execute(message);

		assertEquals(1, contextParameters.size());
		Parameter parameter = contextParameters.get(DEST_NAME);
		assertEquals(DefaultTypes.NUMBER, parameter.getType());
	}
}
