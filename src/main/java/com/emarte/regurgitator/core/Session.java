package com.emarte.regurgitator.core;

public final class Session extends Parameters {
	private static final Log log = Log.getLog(Session.class);

    public Session(Object id) {
        super(id);
    }

	static Session getSession(Object id) {
		if(id == null) {
			throw new IllegalArgumentException("Cannot retrieve session: session id not known");
		}

		Caching.Cache cache = Caching.getCache(Session.class);

		if(cache.hasValue(id)) {
			log.debug("Found existing session for id '" + id + "'");
			return (Session) cache.getValue(id);
		}

		log.debug("Creating and storing new session for id '" + id + "'");
		Session session = new Session(id);
		cache.setValue(id, session);
		return session;
	}
}

