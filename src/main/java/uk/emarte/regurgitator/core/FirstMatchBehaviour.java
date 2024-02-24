/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static uk.emarte.regurgitator.core.Log.getLog;

public final class FirstMatchBehaviour implements RulesBehaviour {
    private static final Log log = getLog(FirstMatchBehaviour.class);

    @Override
    public List<Object> evaluate(List<Object> evaluatedStepIds, List<Object> allStepIds) {
        if(evaluatedStepIds.size() > 0) {
            Object firstMatch = evaluatedStepIds.get(0);
            log.debug("Returning first rule match '{}'", firstMatch);
            return singletonList(firstMatch);
        }

        return emptyList();
    }
}
