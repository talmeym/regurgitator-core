package com.emarte.regurgitator.core;

final class Isolate {
	private static final Log log = Log.getLog(Isolate.class);
	private boolean includeSession;
	private boolean includeParameters;

	public Isolate(boolean includeSession, boolean includeParameters) {
		this.includeSession = includeSession;
		this.includeParameters = includeParameters;
	}

	public Message getNewMessage(Message message) throws RegurgitatorException {
		log.debug("Creating new message: includeSession=" + includeSession + ", includeParameters=" + includeParameters);
		return new Message(message, includeSession, includeParameters);
	}
}
