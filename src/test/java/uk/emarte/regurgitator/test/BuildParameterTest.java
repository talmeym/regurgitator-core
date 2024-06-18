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
    private static final String NAME = "name";
    private static final String ID = "id";
    private static final String CONTEXT = "context";
    private static final String BUILT_VALUE = "builtValue";
    private static final ParameterPrototype PROTOTYPE = new ParameterPrototype(NAME, STRING, REPLACE);

    private final BuildParameter toTest = new BuildParameter(ID, PROTOTYPE, CONTEXT, parameters -> BUILT_VALUE, new ArrayList<>(), false);

    @Test
    public void testBasics() throws RegurgitatorException {
        assertEquals(ID, toTest.getId());
        Message message = new Message(null);
        toTest.execute(message);
        assertParametersContent(message);
    }

    private void assertParametersContent(Message message) {
        Parameters contextParameters = message.getContext(CONTEXT);
        assertEquals(1, contextParameters.size());
        Parameter parameter = contextParameters.get(NAME);
        assertEquals(STRING, parameter.getType());
        assertEquals(BUILT_VALUE, parameter.getValue());
    }

    @Test(expected = RegurgitatorException.class)
    public void testNullBuilderOutput_mandatory() throws RegurgitatorException {
        BuildParameter toTest = new BuildParameter(ID, PROTOTYPE, CONTEXT, parameters -> null, new ArrayList<>(), false);
        toTest.execute(new Message(null));
    }

    @Test
    public void testNullBuilderOutput_optional() throws RegurgitatorException {
        BuildParameter toTest = new BuildParameter(ID, PROTOTYPE, CONTEXT, parameters -> null, new ArrayList<>(), true);
        Message message = new Message(null);
        toTest.execute(message);
        assertEquals(0, message.getContext(CONTEXT).size());
    }
}
