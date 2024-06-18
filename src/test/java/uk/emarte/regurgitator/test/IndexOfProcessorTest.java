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
import static uk.emarte.regurgitator.core.CoreTypes.STRING;

public class IndexOfProcessorTest {
    private static final String THIS = "THIS";
    private static final String THAT = "THAT";
    private static final String THE_OTHER = "THE OTHER";
    private static final List<String> VALUES = Arrays.asList(THIS, THAT, THE_OTHER);

    private static final String CONTEXT = "context";
    private static final String LOCATION = "location";
    private static final ContextLocation SOURCE = new ContextLocation(CONTEXT + ":" + LOCATION);

    private final IndexOfProcessor sourceToTest = new IndexOfProcessor(new ValueSource(SOURCE, null), false);
    private final IndexOfProcessor staticToTest = new IndexOfProcessor(new ValueSource(null, THAT), false);
    private final IndexOfProcessor sourceAndStaticToTest = new IndexOfProcessor(new ValueSource(SOURCE, THAT), false);

    @Test
    public void testSource() throws RegurgitatorException {
        Message message = new Message(null);

        message.getContext(CONTEXT).setValue(LOCATION, STRING, THIS);
        assertEquals(0, sourceToTest.process(VALUES, message));

        message.getContext(CONTEXT).setValue(LOCATION, STRING, THAT);
        assertEquals(1, sourceToTest.process(VALUES, message));

        message.getContext(CONTEXT).setValue(LOCATION, STRING, THE_OTHER);
        assertEquals(2, sourceToTest.process(VALUES, message));

        message.getContext(CONTEXT).setValue(LOCATION, STRING, "SOMETHING ELSE");
        assertEquals(-1, sourceToTest.process(VALUES, message));
    }

    @Test(expected = RegurgitatorException.class)
    public void testInvalidValue_noParam() throws RegurgitatorException {
        Message message = new Message(null);
        sourceToTest.process(VALUES, message);
    }

    @Test
    public void testStatic() throws RegurgitatorException {
        assertEquals(1, staticToTest.process(VALUES, new Message(null)));
    }

    @Test
    public void testSourceAndStatic_sourceFound() throws RegurgitatorException {
        Message message = new Message(null);
        message.getContext(CONTEXT).setValue(LOCATION, STRING, THIS);
        assertEquals(0, sourceAndStaticToTest.process(VALUES, message));
    }

    @Test
    public void testSourceAndStatic_sourceNotFound() throws RegurgitatorException {
        assertEquals(1, sourceAndStaticToTest.process(VALUES, new Message(null)));
    }
}
