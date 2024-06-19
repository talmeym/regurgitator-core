package uk.emarte.regurgitator.test;

import org.junit.Test;
import uk.emarte.regurgitator.core.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ValueSourceTest {
    private static final String LOCATION = "location";
    private static final String VALUE = "here";
    private static final String OTHER_VALUE = "other value";
    private static final String OTHER_LOCATION = "other location";

    @Test
    public void testContextLocation() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(new ContextLocation(LOCATION), null);
        Message message = new Message(null);
        message.getParameters().setValue(LOCATION, VALUE);
        assertEquals(VALUE, toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }

    @Test
    public void testContextLocationNotFound() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(new ContextLocation(LOCATION), null);
        Message message = new Message(null);
        assertNull(toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }

    @Test
    public void testStaticValue() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(null, VALUE);
        Message message = new Message(null);
        assertEquals(VALUE, toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }

    @Test
    public void testStaticValueAsDefault() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(new ContextLocation(LOCATION), VALUE);
        Message message = new Message(null);
        message.getParameters().setValue(OTHER_LOCATION, OTHER_VALUE);
        assertEquals(VALUE, toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }

    @Test
    public void testContextLocationTakesPrecedence() throws RegurgitatorException {
        ValueSource toTest = new ValueSource(new ContextLocation(LOCATION), VALUE);
        Message message = new Message(null);
        message.getParameters().setValue(LOCATION, OTHER_VALUE);
        assertEquals(OTHER_VALUE, toTest.getValue(message, Log.getLog(ValueSourceTest.class)));
    }
}