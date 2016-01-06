package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.ContextLocation.*;

public class Message {
    private final Map<String, Parameters> contextData = new HashMap<String, Parameters>();
    private final ResponseCallBack callback;

    public Message(ResponseCallBack callback) {
        this.callback = callback;
    }

    public Message(Message message, boolean includeSession) {
        this.callback = message.callback;

        if(includeSession && message.hasSession()) {
			contextData.put(SESSION_CONTEXT, message.getSession());
        }
    }

    void setSessionId(Object sessionId) {
        contextData.put(SESSION_CONTEXT, Session.getSession(sessionId));
    }

	public boolean hasSession() {
		return contextData.containsKey(SESSION_CONTEXT);
	}

    public Session getSession() {
		Parameters session = contextData.get(SESSION_CONTEXT);

		if(session == null) {
            throw new IllegalArgumentException("Session not found");
		}

		return (Session) session;
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
