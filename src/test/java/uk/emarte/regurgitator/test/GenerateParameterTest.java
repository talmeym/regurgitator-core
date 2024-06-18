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
import static uk.emarte.regurgitator.core.CoreTypes.NUMBER;

public class GenerateParameterTest {
    private static final String NAME = "paramName";
    private static final String CONTEXT = "context";
    private static final String ID = "id";
    private static final Long GENERATED_VALUE = 123L;
    private static final ParameterPrototype PROTOTYPE = new ParameterPrototype(NAME, NUMBER, REPLACE);

    @Test
    public void testStep() throws RegurgitatorException {
        GenerateParameter toTest = new GenerateParameter(ID, PROTOTYPE, CONTEXT, () -> GENERATED_VALUE, new ArrayList<>(), false);
        assertEquals(ID, toTest.getId());

        Message message = new Message(null);
        Parameters contextParameters = message.getContext(CONTEXT);
        assertEquals(0, contextParameters.size());

        toTest.execute(message);

        assertEquals(1, contextParameters.size());
        Parameter parameter = contextParameters.get(NAME);
        assertEquals(NAME, parameter.getName());
        assertEquals(NUMBER, parameter.getType());
        assertEquals(GENERATED_VALUE, parameter.getValue());
    }

    @Test
    public void testNullGeneratorOutputOptional() throws RegurgitatorException {
        GenerateParameter toTest = new GenerateParameter(ID, PROTOTYPE, CONTEXT, () -> null, new ArrayList<>(), true);
        assertEquals(ID, toTest.getId());

        Message message = new Message(null);
        Parameters contextParameters = message.getContext(CONTEXT);
        assertEquals(0, contextParameters.size());

        toTest.execute(message);

        assertEquals(0, contextParameters.size());
    }

    @Test(expected = RegurgitatorException.class)
    public void testNullGeneratorOutputMandatory() throws RegurgitatorException {
        GenerateParameter toTest = new GenerateParameter(ID, PROTOTYPE, CONTEXT, () -> null, new ArrayList<>(), false);
        assertEquals(ID, toTest.getId());

        Message message = new Message(null);
        Parameters contextParameters = message.getContext(CONTEXT);
        assertEquals(0, contextParameters.size());

        toTest.execute(message);
    }
}
