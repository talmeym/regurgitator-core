/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static uk.emarte.regurgitator.core.Log.getLog;

public final class FirstMatchOnwardsBehaviour implements RulesBehaviour {
    private static final Log log = getLog(FirstMatchOnwardsBehaviour.class);

    @Override
    public List<Object> evaluate(List<Object> evaluatedStepIds, List<Object> allStepIds) {
        if(evaluatedStepIds.size() > 0) {
            List<Object> ids = idAndSubsequent(evaluatedStepIds.get(0), allStepIds);
            log.debug("Returning first rule match and subsequent steps '{}'", ids);
            return ids;
        }

        return emptyList();
    }

    private List<Object> idAndSubsequent(Object id, List<Object> allIds) {
        List<Object> ids = new ArrayList<>();
        boolean found = false;

        for(Object stepId: allIds) {
            if(stepId.equals(id)) {
                found = true;
            }

            if(found) {
                ids.add(stepId);
            }
        }

        return ids;
    }
}
