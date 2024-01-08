/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import static java.lang.Boolean.parseBoolean;
import static uk.emarte.regurgitator.core.Log.getLog;

public final class ExistsBehaviour implements ConditionBehaviour {
    private static final Log log = getLog(ExistsBehaviour.class);

    @Override
    public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean notUsed) {
        boolean expectation = parseBoolean(conditionValue);
        boolean exists = parameter != null;
        log.debug("Parameter " + (exists ? "exists" : "does not exist"));
        return exists == expectation;
    }
}
