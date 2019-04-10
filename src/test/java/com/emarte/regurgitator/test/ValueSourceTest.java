package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ValueSourceTest {
    @Test
    public void testContextLocation() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(new ContextLocation("location"), null);
        Message message = new Message(null);
        message.getParameters().setValue("location", "here");
        assertEquals("here", toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }

    @Test(expected = RegurgitatorException.class)
    public void testContextLocationNotFound() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(new ContextLocation("location"), null);
        Message message = new Message(null);
        toTest.getValue(message, Log.getLog(ValueSourceTest.class));
    }

    @Test
    public void testStaticValue() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(null, "here");
        Message message = new Message(null);
        assertEquals("here", toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }

    @Test
    public void testStaticValueAsDefault() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(new ContextLocation("location"), "here");
        Message message = new Message(null);
        message.getParameters().setValue("otherlocation", "there");
        assertEquals("here", toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }

    @Test
    public void testContextLocationTakesPrecedence() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(new ContextLocation("location"), "here");
        Message message = new Message(null);
        message.getParameters().setValue("location", "there");
        assertEquals("there", toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }
}