/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

import static java.util.Collections.singletonList;
import static uk.emarte.regurgitator.core.Log.getLog;

final class FirstMatchBehaviour implements RulesBehaviour {
    private static final Log log = getLog(FirstMatchBehaviour.class);

    @Override
    public List<Object> evaluate(List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId) {
        if(evaluatedStepIds.size() > 0) {
            Object firstMatch = evaluatedStepIds.get(0);
            log.debug("Returning first rule match '{}'", firstMatch);
            return singletonList(firstMatch);
        }

        if(defaultStepId != null) {
            log.debug("Returning default '{}'", defaultStepId);
            return singletonList(defaultStepId);
        }

        throw new IllegalStateException("No rules evaluated true and no default specified in decision");
    }
}
