/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.*;
import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static org.junit.Assert.assertEquals;

public class IdentifySessionTest {

    private static final String PARAM_VALUE = "value";
    private static final String CONTEXT = "context";
    private static final String PARAM_NAME = "name";

    private final IdentifySession toTest = new IdentifySession("ID", new ValueSource(new ContextLocation(CONTEXT, PARAM_NAME), null));

    @Test
    public void testHappyPath() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(new Parameter(new ParameterPrototype(PARAM_NAME, STRING, REPLACE), PARAM_VALUE));

        toTest.execute(message);

        assertEquals(PARAM_VALUE, message.getSession().getId());
    }

    @Test(expected = RegurgitatorException.class)
    public void testUnhappyPath() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(new Parameter(new ParameterPrototype("other_name", STRING, REPLACE), PARAM_VALUE));

        toTest.execute(message);
    }
}
