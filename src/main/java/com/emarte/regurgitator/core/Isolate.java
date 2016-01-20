package com.emarte.regurgitator.core;

final class Isolate {
	private boolean includeSession;
	private boolean includeParameters;

	public Isolate(boolean includeSession, boolean includeParameters) {
		this.includeSession = includeSession;
		this.includeParameters = includeParameters;
	}

	public Message getNewMessage(Message message) throws RegurgitatorException {
		return new Message(message, includeSession, includeParameters);
	}
}
