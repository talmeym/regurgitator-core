package com.emarte.regurgitator.core;

import java.util.List;

final class Sequence extends Container<Step> implements Step {
    private final Log log = Log.getLog(this);

    Sequence(String id, List<Step> items) {
        super(id, items);
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        for(Step step : getAll()) {
            log.debug("Executing step '" + step.getId() + "'");
            step.execute(message);
        }
    }
}