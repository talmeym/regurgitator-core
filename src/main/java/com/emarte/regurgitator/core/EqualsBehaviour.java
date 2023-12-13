/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

public final class EqualsBehaviour implements ConditionBehaviour {
    private static final Log log = getLog(EqualsBehaviour.class);

    @Override
    public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
        boolean equals = false;

        if(parameter != null) {
            ParameterType<?> parameterType = parameter.getType();
            equals = parameter.getValue().equals(parameterType.convert(conditionValue));
        }

        log.debug("Parameter " + (equals ? "equals" : "does not equal") + " value '{}'", conditionValue);
        return equals == expectation;
    }
}
