/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static uk.emarte.regurgitator.core.CoreTypes.NUMBER;
import static uk.emarte.regurgitator.core.CoreTypes.STRING;

public class RemoveAtIndexProcessorTest {
    private static final String THIS = "THIS";
    private static final String THAT = "THAT";
    private static final String THE_OTHER = "THE OTHER";
    private static final List<String> VALUES = Arrays.asList(THIS, THAT, THE_OTHER);

    private static final String LOCATION = "location";
    private static final String CONTEXT = "context";
    private static final ContextLocation SOURCE = new ContextLocation(CONTEXT + ":" + LOCATION);

    private final RemoveAtIndexProcessor sourceToTest = new RemoveAtIndexProcessor(new ValueSource(SOURCE, null));
    private final RemoveAtIndexProcessor valueToTest = new RemoveAtIndexProcessor(new ValueSource(null, "1"));
    private final RemoveAtIndexProcessor sourceAndValueToTest = new RemoveAtIndexProcessor(new ValueSource(SOURCE, "1"));

    @Test
    public void testSource() throws RegurgitatorException {
        Message message = new Message(null);

        message.getContext(CONTEXT).setValue(LOCATION, NUMBER, 0L);
        assertEquals(Arrays.asList(THAT, THE_OTHER), sourceToTest.process(VALUES, message));

        message.getContext(CONTEXT).setValue(LOCATION, NUMBER, 1L);
        assertEquals(Arrays.asList(THIS, THE_OTHER), sourceToTest.process(VALUES, message));

        message.getContext(CONTEXT).setValue(LOCATION, NUMBER, 2L);
        assertEquals(Arrays.asList(THIS, THAT), sourceToTest.process(VALUES, message));
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_NoParam() throws RegurgitatorException {
        sourceToTest.process(VALUES, new Message(null));
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_NaN() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION, STRING, "abc");
        sourceToTest.process(VALUES, message);
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_tooLow() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION, NUMBER, -1L);
        sourceToTest.process(VALUES, message);
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_tooHigh() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION, NUMBER, 3L);
        sourceToTest.process(VALUES, message);
    }

    @Test
    public void testValue() throws RegurgitatorException {
        assertEquals(Arrays.asList(THIS, THE_OTHER), valueToTest.process(VALUES, new Message(null)));
    }

    @Test
    public void testSourceAndValue_sourceFound() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION, NUMBER, 0L);
        assertEquals(Arrays.asList(THAT, THE_OTHER), sourceAndValueToTest.process(VALUES, message));
    }

    @Test
    public void testSourceAndValue_sourceNotFound() throws RegurgitatorException {
        assertEquals(Arrays.asList(THIS, THE_OTHER), sourceAndValueToTest.process(VALUES, new Message(null)));
    }
}
