/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

import static uk.emarte.regurgitator.core.Log.getLog;

final class Sequence extends Container<Step> implements Step {
    private final Log log = getLog(this);
    private final Isolate isolate;

    Sequence(String id, List<Step> items, Isolate isolate) {
        super(id, items);
        this.isolate = isolate;
    }

    Sequence(String id, Sequence sequence) {
        this(id, sequence.getAll(), sequence.isolate);
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        if(isolate != null) {
            log.debug("Isolating execution with new message");
            message = isolate.getNewMessage(message);
        }

        for(Step step : getAll()) {
            log.debug("Executing step '{}'", step.getId());
            step.execute(message);
        }
    }
}