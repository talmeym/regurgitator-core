/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import static com.emarte.regurgitator.core.ContextLocation.PARAMETER_CONTEXT;
import static com.emarte.regurgitator.core.ContextLocation.SESSION_CONTEXT;

public class Message {
    private final Map<String, Parameters> contextData = new TreeMap<>();
    private final ResponseCallBack callback;

    public Message(ResponseCallBack callback) {
        this.callback = callback;
    }

    public Message(Message message, boolean includeSession, boolean includeParameters) {
        this.callback = message.callback;

        if(includeSession && message.hasSession()) {
            contextData.put(SESSION_CONTEXT, message.getSession());
        }

        if(includeParameters && message.contextData.containsKey(PARAMETER_CONTEXT)) {
            contextData.put(PARAMETER_CONTEXT, message.contextData.get(PARAMETER_CONTEXT));
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
        return contextData.containsKey(contextLocation.getContext()) ? getContext(contextLocation.getContext()).get(contextLocation.getName()) : null;
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

    public Collection<Parameters> contexts() {
        return contextData.values();
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
