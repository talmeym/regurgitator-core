/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.List;

import static java.util.Collections.emptyList;
import static uk.emarte.regurgitator.core.Log.getLog;

public final class AllMatchesBehaviour implements RulesBehaviour {
    private static final Log log = getLog(AllMatchesBehaviour.class);

    @Override
    public List<Object> evaluate(List<Object> evaluatedStepIds, List<Object> allStepIds) {
        if(evaluatedStepIds.size() > 0) {
            log.debug("Returning all rule matches '{}'", evaluatedStepIds);
            return evaluatedStepIds;
        }

        return emptyList();
    }
}
