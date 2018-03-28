package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.*;
import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MatchesBehaviourTest {
    private static final MatchesBehaviour toTest = new MatchesBehaviour();

    @Test
    public void testThis() throws RegurgitatorException {
        Parameter parameter = new Parameter(new ParameterPrototype("to-test", STRING, REPLACE), "value");

        assertTrue(toTest.evaluate(parameter, new Message(null), "^[va]{2}lu[e]{1}$", true));
        assertFalse(toTest.evaluate(parameter, new Message(null), "^[va]{3}lu[e]{2}$", true));
        assertTrue(toTest.evaluate(parameter, new Message(null), "^[va]{3}lu[e]{2}$", false));
    }
}