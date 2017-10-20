package com.emarte.regurgitator.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SessionTest {
    private static final String ID = "hello";

    @Before
    public void setUp() {
        Caching.getCache(Session.class).clearCache();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIdMustBeNonNull() {
        Session.getSession(null);
    }

    @Test
    public void testNonCachedSessionCreation() {
        assertFalse(Caching.getCache(Session.class).contains(ID));
        Session session = Session.getSession(ID);
        assertNotNull(session);
        assertEquals(ID, session.getId());
        assertTrue(Caching.getCache(Session.class).contains(ID));
    }

    @Test
    public void testCachedSessionCreation() {
        assertFalse(Caching.getCache(Session.class).contains(ID));
        Session.getSession(ID);
        assertTrue(Caching.getCache(Session.class).contains(ID));
        Session preLoadedSession = Caching.getCache(Session.class).get(ID);
        assertTrue(preLoadedSession == Session.getSession(ID));
    }
}