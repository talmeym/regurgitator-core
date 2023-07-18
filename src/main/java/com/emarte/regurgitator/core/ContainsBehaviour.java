/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

public final class ContainsBehaviour implements ConditionBehaviour {
    private static final Log log = getLog(ContainsBehaviour.class);

    @Override
    public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
        boolean contains = false;

        if(parameter != null) {
            contains = contains(parameter.getValue(), conditionValue, parameter.getType());
        }

        log.debug("Parameter " + (contains ?  "contain"  : "does not contain") + " value '{}'", conditionValue);
        return contains == expectation;
    }

    private <TYPE> boolean contains(Object existingValue, Object newValue, ParameterType<TYPE> type) {
        return type.contains(type.convert(existingValue), type.convert(newValue));
    }
}
