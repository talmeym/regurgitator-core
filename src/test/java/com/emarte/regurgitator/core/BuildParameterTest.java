/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static junit.framework.Assert.assertEquals;

public class BuildParameterTest {
    private static final String PARAM_NAME = "name";
    private static final ParameterType PARAM_TYPE = STRING;
    private static final ConflictPolicy PARAM_CONFLICT_POL = REPLACE;
    private static final String STEP_ID = "id";
    private static final String PARAM_CONTEXT = "context";
    private static final String BUILT_VALUE = "builtValue";

    private final ParameterPrototype prototype = new ParameterPrototype(PARAM_NAME, PARAM_TYPE, PARAM_CONFLICT_POL);

    private final ValueBuilder valueBuilder = new ValueBuilder() {
        @Override
        public Object build(Message parameters) throws RegurgitatorException {
            return BUILT_VALUE;
        }
    };

    private final BuildParameter toTest = new BuildParameter(STEP_ID, prototype, PARAM_CONTEXT, valueBuilder, null);

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
