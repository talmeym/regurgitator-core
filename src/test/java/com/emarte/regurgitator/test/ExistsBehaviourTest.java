/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.*;
import org.junit.Test;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

public class ExistsBehaviourTest {
    private static final ExistsBehaviour toTest = new ExistsBehaviour();

    @Test
    public void testExists() {
        assertTrue(toTest.evaluate(new Parameter(new ParameterPrototype("to-test", STRING, REPLACE), "value"), new Message(null), "true", false));
        assertTrue(toTest.evaluate(null, new Message(null), "false", true));
        assertFalse(toTest.evaluate(null, new Message(null), "true", false));
    }
}
