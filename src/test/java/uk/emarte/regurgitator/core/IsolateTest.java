package uk.emarte.regurgitator.core;

import org.junit.Test;

import static org.junit.Assert.*;

public class IsolateTest {
    @Test
    public void testNeitherTrue() {
        Isolate toTest = new Isolate(false, false);
        Message message = new Message(null);
        message.setSessionId("hello");
        Parameters parameters = message.getParameters();
        Message newMessage = toTest.getNewMessage(message);
        assertNotSame(newMessage, message);
        assertFalse(newMessage.hasSession());
        assertNotSame(parameters, newMessage.getParameters());
    }

    @Test
    public void testSessionTrue() {
        Isolate toTest = new Isolate(true, false);
        Message message = new Message(null);
        message.setSessionId("hello");
        Session session = message.getSession();
        Parameters parameters = message.getParameters();
        Message newMessage = toTest.getNewMessage(message);
        assertNotSame(newMessage, message);
        assertTrue(newMessage.hasSession());
        assertSame(session, newMessage.getSession());
        assertNotSame(parameters, newMessage.getParameters());
    }

    @Test
    public void testParamsTrue() {
        Isolate toTest = new Isolate(false, true);
        Message message = new Message(null);
        message.setSessionId("hello");
        Parameters parameters = message.getParameters();
        Message newMessage = toTest.getNewMessage(message);
        assertNotSame(newMessage, message);
        assertFalse(newMessage.hasSession());
        assertSame(parameters, newMessage.getParameters());
    }

    @Test
    public void testBothTrue() {
        Isolate toTest = new Isolate(true, true);
        Message message = new Message(null);
        message.setSessionId("hello");
        Session session = message.getSession();
        Parameters parameters = message.getParameters();
        Message newMessage = toTest.getNewMessage(message);
        assertNotSame(newMessage, message);
        assertTrue(newMessage.hasSession());
        assertSame(session, newMessage.getSession());
        assertSame(parameters, newMessage.getParameters());
    }
}