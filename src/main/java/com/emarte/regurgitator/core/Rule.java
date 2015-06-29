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

    boolean evaluate(Message message) throws RegurgitatorException {
		Object id = getId();

		for (Condition condition : getAll()) {
            if (!condition.isMet(message)) {
                log.debug("Condition '" + condition.getId() + "' not met in rule '" + id + "'");
                return false;
            } else {
                log.debug("Condition '" + condition.getId() + "' met in rule '" + id + "'");
			}
        }

        log.debug("All conditions met in rule '" + id + "'");
        return true;
    }
}
