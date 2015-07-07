package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.ContextLocation.*;

public class Message {
    private final Map<String, Parameters> contextData = new HashMap<String, Parameters>();
    private final ResponseCallBack callback;

    public Message(ResponseCallBack callback) {
        this.callback = callback;
    }

    public Message(ResponseCallBack callback, Object sessionId) {
        this.callback = callback;

        if(sessionId == null) {
            throw new IllegalArgumentException("Session id cannot be null");
        }

        setSessionId(sessionId);
    }

    void setSessionId(Object sessionId) {
        contextData.put(SESSION_CONTEXT, SessionCache.getSession(sessionId));
    }

    public Session getSession() {
        return (Session) contextData.get(SESSION_CONTEXT);
    }

    public Parameters getParameters() {
        return getContext(PARAMETER_CONTEXT);
    }

    public ResponseCallBack getResponseCallback() {
        return callback;
    }

    public Parameter getContextValue(ContextLocation contextLocation) {
        return getContext(contextLocation.getContext()).get(contextLocation.getName());
    }

    public Parameters getContext(String context) {
        if(SESSION_CONTEXT.equals(context)) {
            return getSession();
        }

        if (!contextData.containsKey(context)) {
            contextData.put(context, new Parameters(context));
        }

        return contextData.get(context);
    }

	@Override
	public String toString() {
		StringBuilder buffer = new StringBuilder("message[");

		for(Parameters context: contextData.values()) {
			buffer.append(context.toString()).append(",");
		}

		buffer.append("]");
		return buffer.toString();
	}
}
