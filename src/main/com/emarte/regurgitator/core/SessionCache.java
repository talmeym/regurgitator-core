package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Caching.Cache;

public class SessionCache {
    private static final Log log = Log.getLog(SessionCache.class);

    static Session getSession(Object id) {
		Cache cache = Caching.getCache(SessionCache.class);

        if(id == null) {
            throw new IllegalArgumentException("Cannot retrieve session: session id not known");
        }

		if(cache.hasValue(id)) {
			log.debug("Found existing session for id '" + id + "'");
			return (Session) cache.getValue(id);
		}

		log.debug("Creating new session for id '" + id + "'");
		Session session = new Session(id);
		cache.setValue(id, session);
		return session;
    }
}
