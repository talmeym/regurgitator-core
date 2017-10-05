package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.Log.getLog;

final class Decision extends Container<Step> implements Step {
    private final Log log = getLog(this);
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
        List<Object> evaluatedIds = new ArrayList<Object>();
        log.debug("Evaluating rules");

        for (Rule rule : rules) {
            if (rule.evaluate(message)) {
                log.debug("Rule '{}' passed", rule.getId());
                evaluatedIds.add(rule.getStepId());
            } else {
                log.debug("Rule '{}'' did not pass", rule.getId());
            }
        }

        log.debug("Applying rules behaviour");
        List<Step> steps = get(behaviour.evaluate(evaluatedIds, ids(), defaultStepId));

        for(Step step : steps) {
            log.debug("Executing '{}'", step.getId());
            step.execute(message);
        }
    }
}