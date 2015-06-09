package com.emarte.regurgitator.core;

import java.util.*;

final class Decision extends Container<Step> implements Step {
    private static  final Log log = Log.getLog(Decision.class);

    private final Rules rules;

    Decision(String id, List<Step> steps, Rules rules) {
        super(id, steps);
        this.rules = rules;
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
		if(rules != null) {
            log.debug("Applying rules '" + rules.getId() + "'");
            return get(rules.runRules(message, ids()));
        }

        log.debug("Running all steps");
		return getAll();
    }
}