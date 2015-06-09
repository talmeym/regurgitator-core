package com.emarte.regurgitator.core;

import java.util.List;

final class Rule extends Container<Condition> {
    private final static Log log = Log.getLog(Rule.class);
    private final Object stepId;

    Rule(String id, List<Condition> conditions, Object stepId) {
        super(id, conditions);
        this.stepId = stepId;
    }

    Object getStepId() {
        return stepId;
    }

    boolean evaluate(Message message) {
        for (Condition condition : getAll()) {
            if (!condition.isMet(message)) {
                log.debug("Condition '" + condition.getId() + "' not met in rule '" + getId() + "'");
                return false;
            } else {
                log.debug("Condition '" + condition.getId() + "' met in rule '" + getId() + "'");
			}
        }

        log.debug("All conditions met in rule '" + getId() + "'");
        return true;
    }
}
