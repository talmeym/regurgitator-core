package com.emarte.regurgitator.core;

import java.util.List;

import static com.emarte.regurgitator.core.Log.getLog;

final class Rule extends Container<Condition> {
    private final Log log = getLog(this);
    private final Object stepId;

    Rule(String id, List<Condition> conditions, Object stepId) {
        super(id, conditions);
        this.stepId = stepId;
    }

    Object getStepId() {
        return stepId;
    }

    boolean evaluate(Message message) throws RegurgitatorException {
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
