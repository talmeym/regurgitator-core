/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import static uk.emarte.regurgitator.core.CacheProvider.Cache;
import static uk.emarte.regurgitator.core.Log.getLog;

public final class Session extends Parameters {
    private static final Log log = getLog(Session.class);

    private Session(Object id) {
        super(id);
    }

    static Session getSession(Object id) {
        if(id == null) {
            throw new IllegalArgumentException("Cannot retrieve session: session id not found");
        }

        Cache<Session> cache = Caching.getCache(Session.class);

        if(cache.contains(id)) {
            log.debug("Found existing session for id '{}'", id);
            return cache.get(id);
        }

        log.debug("Creating and storing new session for id '{}'", id);
        Session session = new Session(id);
        cache.set(id, session);
        return session;
    }
}

