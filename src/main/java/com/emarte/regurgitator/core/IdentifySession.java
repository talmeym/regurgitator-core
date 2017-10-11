/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

public final class IdentifySession extends Identifiable implements Step {
    private final Log log = getLog(this);
    private final ValueSource valueSource;

    public IdentifySession(Object id, ValueSource valueSource) {
        super(id);
        this.valueSource = valueSource;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        Object value = valueSource.getValue(message, log);
        log.debug("Setting message session id to '{}'", value);

        if(!message.hasSession()) {
            message.setSessionId(value);
        } else {
            log.warn("Session already identified");
        }
    }
}
