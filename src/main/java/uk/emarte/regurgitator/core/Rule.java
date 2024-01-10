/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

import static uk.emarte.regurgitator.core.Log.getLog;

public final class Rule extends Container<Condition> {
    private final Log log = getLog(this);
    private final Object stepId;

    public Rule(String id, List<Condition> conditions, Object stepId) {
        super(id, conditions);
        this.stepId = stepId;
    }

    public Object getStepId() {
        return stepId;
    }

    public boolean evaluate(Message message) throws RegurgitatorException {
        log.debug("Evaluating conditions");

        for (Condition condition : getAll()) {
            if (!condition.isMet(message)) {
                log.debug("Condition '{}' not met", condition.getId());
                return false;
            } else {
                log.debug("Condition '{}' met", condition.getId());
            }
        }

        log.debug("All conditions met");
        return true;
    }
}
