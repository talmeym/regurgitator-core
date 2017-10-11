/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static junit.framework.Assert.assertEquals;

public class IndexOfProcessorTest {
    private final IndexOfProcessor sourceToTest = new IndexOfProcessor(new ValueSource(new ContextLocation("context:location"), null), false);
    private final IndexOfProcessor staticToTest = new IndexOfProcessor(new ValueSource(null, "THAT"), false);
    private final IndexOfProcessor sourceAndStaticToTest = new IndexOfProcessor(new ValueSource(new ContextLocation("context:location"), "THAT"), false);

    @Test
    public void testSource() throws RegurgitatorException {
        List<String> values = Arrays.asList("THIS", "THAT", "THE OTHER");

        Message message = new Message(null);

        message.getContext("context").setValue("location", STRING, "THIS");
        assertEquals(0L, sourceToTest.process(values, message));

        message.getContext("context").setValue("location", STRING, "THAT");
        assertEquals(1L, sourceToTest.process(values, message));

        message.getContext("context").setValue("location", STRING, "THE OTHER");
        assertEquals(2L, sourceToTest.process(values, message));

        message.getContext("context").setValue("location", STRING, "SOMETHING ELSE");
        assertEquals(-1L, sourceToTest.process(values, message));
    }

    @Test
    public void testStatic() throws RegurgitatorException {
        assertEquals(1L, staticToTest.process(Arrays.asList("THIS", "THAT", "THE OTHER"), new Message(null)));
    }

    @Test
    public void testSourceAndStatic_sourceFound() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext("context").setValue("location", STRING, "THIS");
        assertEquals(0L, sourceAndStaticToTest.process(Arrays.asList("THIS", "THAT", "THE OTHER"), message));
    }

    @Test
    public void testSourceAndStatic_sourceNotFound() throws RegurgitatorException {
        List<String> values = Arrays.asList("THIS", "THAT", "THE OTHER");
        assertEquals(1L, sourceAndStaticToTest.process(values, new Message(null)));
    }
}
