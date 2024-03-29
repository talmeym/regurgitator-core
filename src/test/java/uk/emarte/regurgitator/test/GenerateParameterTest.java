/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static uk.emarte.regurgitator.core.ConflictPolicy.REPLACE;

public class GenerateParameterTest {
    private static final String DEST_NAME = "newName";
    private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
    private static final String PARAM_CONTEXT = "context";
    private static final String SOURCE_ID = "sourceId";

    private final ParameterPrototype destPrototype = new ParameterPrototype(DEST_NAME, CoreTypes.NUMBER, PARAM_CONFLICT_POL);

    private static final ValueGenerator NUMBER = new NumberGenerator();

    private final GenerateParameter toTest = new GenerateParameter(SOURCE_ID, destPrototype, PARAM_CONTEXT, NUMBER, new ArrayList<>());

    @Test
    public void testStep() throws RegurgitatorException {
        assertEquals(SOURCE_ID, toTest.getId());
        Message message = new Message(null);

        Parameters contextParameters = message.getContext(PARAM_CONTEXT);
        assertEquals(0, contextParameters.size());

        toTest.execute(message);

        assertEquals(1, contextParameters.size());
        Parameter parameter = contextParameters.get(DEST_NAME);
        assertEquals(CoreTypes.NUMBER, parameter.getType());
    }
}
