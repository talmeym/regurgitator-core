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
import static uk.emarte.regurgitator.core.CoreTypes.STRING;

public class BuildParameterTest {
    private static final String PARAM_NAME = "name";
    private static final ParameterType<?> PARAM_TYPE = STRING;
    private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
    private static final String STEP_ID = "id";
    private static final String PARAM_CONTEXT = "context";
    private static final String BUILT_VALUE = "builtValue";

    private final ParameterPrototype prototype = new ParameterPrototype(PARAM_NAME, PARAM_TYPE, PARAM_CONFLICT_POL);

    private final ValueBuilder builder = parameters -> BUILT_VALUE;

    private final BuildParameter toTest = new BuildParameter(STEP_ID, prototype, PARAM_CONTEXT, builder, new ArrayList<>());

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
}
