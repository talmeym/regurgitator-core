/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.ExtractProcessor;
import uk.emarte.regurgitator.core.RegurgitatorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ExtractProcessorTest {
    public static final Object NULL = null;

    @Test
    public void testHappyPath() throws RegurgitatorException {
        ExtractProcessor toTest = new ExtractProcessor("This is {0} sentence", 0);
        assertEquals("a", toTest.process("This is a sentence", null));
    }

    @Test
    public void testPassThrough() throws RegurgitatorException {
        ExtractProcessor toTest = new ExtractProcessor("This is {0} sentence", -1);
        assertNull(toTest.process(NULL, null));
    }

    @Test(expected = RegurgitatorException.class)
    public void testIndexInvalid_tooLow() throws RegurgitatorException {
        ExtractProcessor toTest = new ExtractProcessor("This is {0} sentence", -1);
        toTest.process("This is a sentence", null);
    }

    @Test(expected = RegurgitatorException.class)
    public void testIndexInvalid_tooHigh() throws RegurgitatorException {
        ExtractProcessor toTest = new ExtractProcessor("This is {0} sentence", 1);
        toTest.process("This is a sentence", null);
    }

    @Test(expected = RegurgitatorException.class)
    public void testParseException() throws RegurgitatorException {
        ExtractProcessor toTest = new ExtractProcessor("This is {0} sentence", 0);
        toTest.process("THAT is a sentence", null);
    }
}
