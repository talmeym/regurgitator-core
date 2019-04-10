package com.emarte.regurgitator.core;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsolateTest {
    @Test
    public void testNeitherTrue() {
        Isolate toTest = new Isolate(false, false);
        Message message = new Message(null);
        message.setSessionId("hello");
        Parameters parameters = message.getParameters();
        Message newMessage = toTest.getNewMessage(message);
        assertFalse(newMessage == message);
        assertFalse(newMessage.hasSession());
        assertFalse(parameters == newMessage.getParameters());
    }

    @Test
    public void testSessionTrue() {
        Isolate toTest = new Isolate(true, false);
        Message message = new Message(null);
        message.setSessionId("hello");
        Session session = message.getSession();
        Parameters parameters = message.getParameters();
        Message newMessage = toTest.getNewMessage(message);
        assertFalse(newMessage == message);
        assertTrue(newMessage.hasSession());
        assertTrue(session == newMessage.getSession());
        assertFalse(parameters == newMessage.getParameters());
    }

    @Test
    public void testParamsTrue() {
        Isolate toTest = new Isolate(false, true);
        Message message = new Message(null);
        message.setSessionId("hello");
        Parameters parameters = message.getParameters();
        Message newMessage = toTest.getNewMessage(message);
        assertFalse(newMessage == message);
        assertFalse(newMessage.hasSession());
        assertTrue(parameters == newMessage.getParameters());
    }

    @Test
    public void testBothTrue() {
        Isolate toTest = new Isolate(true, true);
        Message message = new Message(null);
        message.setSessionId("hello");
        Session session = message.getSession();
        Parameters parameters = message.getParameters();
        Message newMessage = toTest.getNewMessage(message);
        assertFalse(newMessage == message);
        assertTrue(newMessage.hasSession());
        assertTrue(session == newMessage.getSession());
        assertTrue(parameters == newMessage.getParameters());
    }
}