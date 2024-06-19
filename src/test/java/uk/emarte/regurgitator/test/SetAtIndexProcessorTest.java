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

public class SetAtIndexProcessorTest {
    private static final String THIS = "THIS";
    private static final String THAT = "THAT";
    private static final String THE_OTHER = "THE OTHER";
    private static final List<String> VALUES = Arrays.asList(THIS, THAT, THE_OTHER);
    private static final String NEW = "NEW";

    private static final String LOCATION1 = "location1";
    private static final String LOCATION2 = "location2";
    private static final String CONTEXT = "context";
    private static final ContextLocation SOURCE1 = new ContextLocation(CONTEXT + ":" + LOCATION1);
    private static final ContextLocation SOURCE2 = new ContextLocation(CONTEXT + ":" + LOCATION2);

    private final SetAtIndexProcessor sourceSourceToTest = new SetAtIndexProcessor(new ValueSource(SOURCE1, null), new ValueSource(SOURCE2, null));
    private final SetAtIndexProcessor valueValueToTest = new SetAtIndexProcessor(new ValueSource(null, "2"), new ValueSource(null, NEW));
    private final SetAtIndexProcessor sourceAndValueToTest = new SetAtIndexProcessor(new ValueSource(SOURCE1, "0"), new ValueSource(SOURCE2, NEW));

    @Test
    public void testSourceSource() throws RegurgitatorException {
        Message message = new Message(null);

        message.getContext(CONTEXT).setValue(LOCATION1, NUMBER, 0L);
        message.getContext(CONTEXT).setValue(LOCATION2, STRING, NEW);
        assertEquals(Arrays.asList(NEW, THAT, THE_OTHER), sourceSourceToTest.process(VALUES, message));
        message.getContext(CONTEXT).setValue(LOCATION1, NUMBER, 1L);
        assertEquals(Arrays.asList(THIS, NEW, THE_OTHER), sourceSourceToTest.process(VALUES, message));
        message.getContext(CONTEXT).setValue(LOCATION1, NUMBER, 2L);
        assertEquals(Arrays.asList(THIS, THAT, NEW), sourceSourceToTest.process(VALUES, message));
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_NoParam() throws RegurgitatorException {
        sourceSourceToTest.process(VALUES, new Message(null));
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidValue_NoParam() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION1, NUMBER, 0L);
        sourceSourceToTest.process(VALUES, message);
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_NaN() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION2, STRING, "abc");
        sourceSourceToTest.process(VALUES, message);
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_tooLow() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION2, NUMBER, -1L);
        sourceSourceToTest.process(VALUES, message);
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidIndex_tooHigh() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION2, NUMBER, 3L);
        sourceSourceToTest.process(VALUES, message);
    }

    @Test
    public void testValue() throws RegurgitatorException {
        assertEquals(Arrays.asList(THIS, THAT, NEW), valueValueToTest.process(VALUES, new Message(null)));
    }

    @Test
    public void testSourceAndValue_sourceFound() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION1, NUMBER, 1L);
        assertEquals(Arrays.asList(THIS, NEW, THE_OTHER), sourceAndValueToTest.process(VALUES, message));
    }

    @Test
    public void testSourceAndValue_sourceNotFound() throws RegurgitatorException {
        assertEquals(Arrays.asList(NEW, THAT, THE_OTHER), sourceAndValueToTest.process(VALUES, new Message(null)));
    }
}
