/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.RegurgitatorException;
import uk.emarte.regurgitator.core.SizeProcessor;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class SizeProcessorTest {
    private final SizeProcessor toTest = new SizeProcessor(false);
    private final SizeProcessor toTestMinusOne = new SizeProcessor(true);

    @Test
    public void testProcessor() throws RegurgitatorException {
        assertEquals(3, toTest.process(Arrays.asList("one", "two", "three"), null));
        assertEquals(3, toTest.process(new HashSet<>(Arrays.asList("one", "two", "three")), null));
    }

    @Test
    public void testMinusOne() throws RegurgitatorException {
        assertEquals(2, toTestMinusOne.process(Arrays.asList("one", "two", "three"), null));
        assertEquals(2, toTestMinusOne.process(new HashSet<>(Arrays.asList("one", "two", "three")), null));
    }
}
