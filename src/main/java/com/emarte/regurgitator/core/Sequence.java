package com.emarte.regurgitator.core;

import java.util.List;

final class Sequence extends Container<Step> implements Step {
    private static final Log log = Log.getLog(Sequence.class);

    Sequence(String id, List<Step> items) {
        super(id, items);
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        for(Step step : getAll()) {
            log.debug("Executing '" + step.getId() + "'");
            step.execute(message);
        }
    }
}