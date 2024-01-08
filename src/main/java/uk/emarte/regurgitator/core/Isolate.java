/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import static uk.emarte.regurgitator.core.Log.getLog;

final class Isolate {
    private static final Log log = getLog(Isolate.class);
    private final boolean includeSession;
    private final boolean includeParameters;

    Isolate(boolean includeSession, boolean includeParameters) {
        this.includeSession = includeSession;
        this.includeParameters = includeParameters;
    }

    Message getNewMessage(Message message) {
        log.debug("Creating new message: includeSession={}, includeParameters={}", includeSession, includeParameters);
        return new Message(message, includeSession, includeParameters);
    }
}
