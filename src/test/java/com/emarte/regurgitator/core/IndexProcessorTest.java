package com.emarte.regurgitator.core;

import org.junit.Test;

import java.util.*;

import static com.emarte.regurgitator.core.CoreTypes.NUMBER;
import static junit.framework.Assert.assertEquals;

public class IndexProcessorTest {
    private final IndexProcessor sourceToTest = new IndexProcessor(new ValueSource(new ContextLocation("context:location"), null));
    private final IndexProcessor valueToTest = new IndexProcessor(new ValueSource(null, "1"));
    private final IndexProcessor sourceAndValueToTest = new IndexProcessor(new ValueSource(new ContextLocation("context:location"), "1"));

    @Test
    public void testSource() throws RegurgitatorException {
        List<String> values = Arrays.asList("THIS", "THAT", "THE OTHER");

        Message message = new Message(null);

        message.getContext("context").setValue("location", NUMBER, 0L);
        assertEquals("THIS", sourceToTest.process(values, message));

        message.getContext("context").setValue("location", NUMBER, 1L);
        assertEquals("THAT", sourceToTest.process(values, message));

        message.getContext("context").setValue("location", NUMBER, 2L);
        assertEquals("THE OTHER", sourceToTest.process(values, message));
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_minusOne() throws RegurgitatorException {
        List<String> values = Arrays.asList("THIS", "THAT", "THE OTHER");
        Message message = new Message(null);
        message.getContext("context").setValue("location", NUMBER, -1L);
        sourceToTest.process(values, message);
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_tooHigh() throws RegurgitatorException {
        List<String> values = Arrays.asList("THIS", "THAT", "THE OTHER");
        Message message = new Message(null);
        message.getContext("context").setValue("location", NUMBER, 3L);
        sourceToTest.process(values, message);
    }

    @Test
    public void testValue() throws RegurgitatorException {
        assertEquals("THAT", valueToTest.process(Arrays.asList("THIS", "THAT", "THE OTHER"), new Message(null)));
    }

    @Test
    public void testSourceAndValue_sourceFound() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext("context").setValue("location", NUMBER, 0L);
        assertEquals("THIS", sourceAndValueToTest.process(Arrays.asList("THIS", "THAT", "THE OTHER"), message));
    }

    @Test
    public void testSourceAndValue_sourceNotFound() throws RegurgitatorException {
        assertEquals("THAT", sourceAndValueToTest.process(Arrays.asList("THIS", "THAT", "THE OTHER"), new Message(null)));
    }
}
