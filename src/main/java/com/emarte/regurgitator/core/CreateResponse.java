/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.List;

import static com.emarte.regurgitator.core.Log.getLog;

public final class CreateResponse extends Identifiable implements Step {
    private final Log log = getLog(this);
    private final ValueSource valueSource;
    private final List<ValueProcessor> processors;

    public CreateResponse(String id, ValueSource valueSource, List<ValueProcessor> processors) {
        super(id);
        this.valueSource = valueSource;
        this.processors = processors;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        execute(message, log);
    }

    public void execute(Message message, Log log) throws RegurgitatorException {
        Object value = valueSource.getValue(message, log);

        if(processors.size() > 0) {
            for(ValueProcessor processor: processors) {
                value = processor.process(value, message);
            }
        }

        log.debug("Sending response to callback");
        message.getResponseCallback().respond(message, value);
    }
}
