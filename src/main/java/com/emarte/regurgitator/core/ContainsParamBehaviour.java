/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

final class ContainsParamBehaviour implements ConditionBehaviour {
    private static final Log log = getLog(ContainsParamBehaviour.class);

    @Override
    @SuppressWarnings("unchecked")
    public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
        boolean contains = false;

        if(parameter != null) {
            Parameter comparisonParameter = message.getContextValue(new ContextLocation(conditionValue));

            if(comparisonParameter != null) {
                ParameterType parameterType = parameter.getType();
                contains = parameterType.contains(parameter.getValue(), parameterType.convert(comparisonParameter.getValue()));
            }
        }

        log.debug("Parameter " + (contains ? "contains" : "does not contain") + " parameter '{}'", conditionValue);
        return contains == expectation;
    }
}
