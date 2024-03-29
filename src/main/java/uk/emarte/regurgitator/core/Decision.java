/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.List;

import static uk.emarte.regurgitator.core.Log.getLog;

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
        List<Object> evaluatedIds = new ArrayList<>();
        log.debug("Evaluating rules");

        for (Rule rule : rules) {
            if (rule.evaluate(message)) {
                log.debug("Rule '{}' passed", rule.getId());
                evaluatedIds.add(rule.getStepId());
            } else {
                log.debug("Rule '{}' did not pass", rule.getId());
            }
        }

        log.debug("Applying rules behaviour");
        List<Step> steps = get(behaviour.evaluate(evaluatedIds, ids()));

        if(steps.isEmpty()) {
            if(defaultStepId != null) {
                log.debug("Using default step '{}'", defaultStepId);
                steps.add(get(defaultStepId));
            } else {
                throw new RegurgitatorException("No rules evaluated true and no default specified in decision");
            }
        }

        for(Step step : steps) {
            log.debug("Executing '{}'", step.getId());
            step.execute(message);
        }
    }
}