package com.emarte.regurgitator.core;

import java.util.*;

final class Decision extends Container<Step> implements Step {
    private static  final Log log = Log.getLog(Decision.class);

	private final List<Rule> rules;
    private final RulesBehaviour behaviour;
	private final Object defaultStepId;

	Decision(String id, List<Step> steps, List<Rule> rules, RulesBehaviour behaviour, Object defaultStepId) {
        super(id, steps);
		this.rules = rules;
        this.behaviour = behaviour;
		this.defaultStepId = defaultStepId;
    }

    @Override
    public void execute(Message message) throws RegurgitatorException {
        List<Step> steps = getStepsToRun(message);

        for(Step step : steps) {
            log.debug("Executing '" + step.getId() + "'");
            step.execute(message);
        }
    }

    private List<Step> getStepsToRun(Message message) {
		List<Object> evaluatedIds = new ArrayList<Object>();
		log.debug("Evaluating all rules");

		for (Rule rule : rules) {
			if (rule.evaluate(message)) {
				log.debug("Rule '" + rule.getId() + "\' passed");
				evaluatedIds.add(rule.getStepId());
			} else {
				log.debug("Rule '" + rule.getId() + "\' did not pass");
			}
		}

		return get(behaviour.evaluate(getId(), evaluatedIds, ids(), defaultStepId));
    }
}