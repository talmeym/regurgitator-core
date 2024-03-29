/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.ExistsBehaviour;
import uk.emarte.regurgitator.core.Message;
import uk.emarte.regurgitator.core.Parameter;
import uk.emarte.regurgitator.core.ParameterPrototype;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static uk.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static uk.emarte.regurgitator.core.CoreTypes.STRING;

public class ExistsBehaviourTest {
    private static final ExistsBehaviour toTest = new ExistsBehaviour();

    @Test
    public void testExists() {
        assertTrue(toTest.evaluate(new Parameter(new ParameterPrototype("to-test", STRING, REPLACE), "value"), new Message(null), "true", false));
        assertTrue(toTest.evaluate(null, new Message(null), "false", true));
        assertFalse(toTest.evaluate(null, new Message(null), "true", false));
    }
}
