/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

public final class CreateResponse extends Identifiable implements Step {
    private final Log log = getLog(this);
    private final ValueSource valueSource;
    private final ValueProcessor valueProcessor;

    CreateResponse(String id, ValueSource valueSource, ValueProcessor valueProcessor) {
        super(id);
        this.valueSource = valueSource;
        this.valueProcessor = valueProcessor;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        execute(message, log);
    }

    public void execute(Message message, Log log) throws RegurgitatorException {
        Object value = valueSource.getValue(message, log);

        if(valueProcessor != null) {
            value = valueProcessor.process(value, message);
        }

        log.debug("Sending response to callback");
        message.getResponseCallback().respond(message, value);
    }
}
