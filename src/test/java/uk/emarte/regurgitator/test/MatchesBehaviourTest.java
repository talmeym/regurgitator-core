/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.MatchesBehaviour;
import uk.emarte.regurgitator.core.Message;
import uk.emarte.regurgitator.core.Parameter;
import uk.emarte.regurgitator.core.ParameterPrototype;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static uk.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static uk.emarte.regurgitator.core.CoreTypes.STRING;

public class MatchesBehaviourTest {
    private static final MatchesBehaviour toTest = new MatchesBehaviour();

    @Test
    public void testThis() {
        Parameter parameter = new Parameter(new ParameterPrototype("to-test", STRING, REPLACE), "value");

        assertTrue(toTest.evaluate(parameter, new Message(null), "^[va]{2}lu[e]{1}$", true));
        assertFalse(toTest.evaluate(parameter, new Message(null), "^[va]{3}lu[e]{2}$", true));
        assertTrue(toTest.evaluate(parameter, new Message(null), "^[va]{3}lu[e]{2}$", false));
    }
}