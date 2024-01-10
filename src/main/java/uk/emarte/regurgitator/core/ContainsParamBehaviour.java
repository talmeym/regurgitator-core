/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import static uk.emarte.regurgitator.core.Log.getLog;

final class ContainsParamBehaviour implements ConditionBehaviour {
    private static final Log log = getLog(ContainsParamBehaviour.class);

    @Override
    public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
        boolean contains = false;

        if(parameter != null) {
            Parameter comparisonParameter = message.getContextValue(new ContextLocation(conditionValue));

            if(comparisonParameter != null) {
                contains = contains(parameter.getValue(), comparisonParameter.getValue(), parameter.getType());
            }
        }

        log.debug("Parameter " + (contains ? "contains" : "does not contain") + " parameter '{}'", conditionValue);
        return contains == expectation;
    }

    private <TYPE> boolean contains(Object existingValue, Object newValue, ParameterType<TYPE> type) {
        return type.contains(type.convert(existingValue), type.convert(newValue));
    }
}
