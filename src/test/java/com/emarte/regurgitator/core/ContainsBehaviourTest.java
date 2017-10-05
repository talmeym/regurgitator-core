package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.Arrays;

import static com.emarte.regurgitator.core.ConflictPolicy.REPLACE;
import static com.emarte.regurgitator.core.CoreTypes.*;
import static junit.framework.Assert.*;

public class ContainsBehaviourTest {
    private static ContainsBehaviour toTest = new ContainsBehaviour();

    @Test
    public void testThis() {
        ParameterPrototype stringPrototype = new ParameterPrototype("to-test", STRING, REPLACE);

        assertTrue(toTest.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "lue", true));
        assertTrue(toTest.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "valve", false));
        assertFalse(toTest.evaluate(new Parameter(stringPrototype, "value"), new Message(null), "valve", true));

        ParameterPrototype numberPrototype = new ParameterPrototype("to-test", NUMBER, REPLACE);

        assertTrue(toTest.evaluate(new Parameter(numberPrototype, 5L), new Message(null), "4", true));
        assertTrue(toTest.evaluate(new Parameter(numberPrototype, 5L), new Message(null), "6", false));
        assertFalse(toTest.evaluate(new Parameter(numberPrototype, 5L), new Message(null), "6", true));

        ParameterPrototype decimalPrototype = new ParameterPrototype("to-test", DECIMAL, REPLACE);

        assertTrue(toTest.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "4.9", true));
        assertTrue(toTest.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.1", false));
        assertFalse(toTest.evaluate(new Parameter(decimalPrototype, 5.0d), new Message(null), "5.1", true));

        ParameterPrototype listStringPrototype = new ParameterPrototype("to-test", LIST_OF_STRING, REPLACE);

        assertTrue(toTest.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "one,two", true));
        assertTrue(toTest.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "three", false));
        assertFalse(toTest.evaluate(new Parameter(listStringPrototype, Arrays.asList("one", "two")), new Message(null), "three", true));
    }
}
