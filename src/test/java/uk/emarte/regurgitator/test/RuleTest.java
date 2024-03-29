/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RuleTest {

    private static final List<Condition> passingConditions = new ArrayList<>();
    private static final List<Condition> failingConditions = new ArrayList<>();

    private static final ContextLocation CONTEXT_LOCATION = new ContextLocation("context:location");

    private static final String VALUE = "value";

    static {
        passingConditions.add(new Condition("1", CONTEXT_LOCATION, VALUE, true, new PassingBehaviour()));
        passingConditions.add(new Condition("2", CONTEXT_LOCATION, VALUE, true, new PassingBehaviour()));
        passingConditions.add(new Condition("3", CONTEXT_LOCATION, VALUE, true, new PassingBehaviour()));

        failingConditions.add(new Condition("4", CONTEXT_LOCATION, VALUE, true, new PassingBehaviour()));
        failingConditions.add(new Condition("5", CONTEXT_LOCATION, VALUE, true, new FailingBehaviour()));
        failingConditions.add(new Condition("6", CONTEXT_LOCATION, VALUE, true, new PassingBehaviour()));
    }

    private final Rule passingToTest = new Rule("7", passingConditions, "9");
    private final Rule failingToTest = new Rule("8", failingConditions, "10");

    @Test
    public void testPass() throws RegurgitatorException {
        assertEquals("9", passingToTest.getStepId());
        assertTrue(passingToTest.evaluate(new Message(null)));
    }

    @Test
    public void testFail() throws RegurgitatorException {
        assertEquals("10", failingToTest.getStepId());
        assertFalse(failingToTest.evaluate(new Message(null)));
    }

    private static class PassingBehaviour implements ConditionBehaviour {
        @Override
        public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
            return true;
        }
    }

    private static class FailingBehaviour implements ConditionBehaviour {
        @Override
        public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
            return false;
        }
    }
}
