package com.emarte.regurgitator.core;

import java.util.*;

final class Rules extends Container<Rule> {
    private static final Log log = Log.getLog(Rules.class);

    private final RulesBehaviour behaviour;
	private final Object defaultStepId;

    Rules(String id, List<Rule> rules, RulesBehaviour behaviour, Object defaultStepId) {
        super(id, rules);
		this.behaviour = behaviour;
		this.defaultStepId = defaultStepId;
	}

    List<Object> runRules(Message message, List<Object> allStepIds) {
        List<Object> evaluatedIds = new ArrayList<Object>();
		log.debug("Evaluating all rules");

        for (Rule rule : getAll()) {
            if (rule.evaluate(message)) {
                log.debug("Rule '" + rule.getId() + "\' passed");
                evaluatedIds .add(rule.getStepId());
            } else {
				log.debug("Rule '" + rule.getId() + "\' did not pass");
			}
        }

		return behaviour.evaluate(getId(), evaluatedIds, allStepIds, defaultStepId);
    }
}
