/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.ExtractProcessor;
import uk.emarte.regurgitator.core.RegurgitatorException;

import static org.junit.Assert.assertEquals;

public class ExtractProcessorTest {
    @Test
    public void testProcessor() throws RegurgitatorException {
        ExtractProcessor toTest = new ExtractProcessor("This is {0} sentence", 0);
        assertEquals("a", toTest.process("This is a sentence", null));
    }
}
