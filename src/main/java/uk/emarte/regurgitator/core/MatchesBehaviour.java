/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static uk.emarte.regurgitator.core.Log.getLog;
import static uk.emarte.regurgitator.core.StringType.stringify;

public final class MatchesBehaviour implements ConditionBehaviour {
    private static final Log log = getLog(MatchesBehaviour.class);

    @Override
    public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
        boolean matches = false;

        if(parameter != null) {
            Pattern pattern = Pattern.compile(conditionValue);
            Matcher matcher = pattern.matcher(stringify(parameter.getValue()));
            matches = matcher.find();
        }

        log.debug("Parameter " + (matches ? "matches" : "does not match") + " regex '{}'", conditionValue);
        return matches == expectation;
    }
}
