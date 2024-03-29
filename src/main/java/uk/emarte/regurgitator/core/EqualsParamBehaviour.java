/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import static uk.emarte.regurgitator.core.Log.getLog;

class EqualsParamBehaviour implements ConditionBehaviour {
    private static final Log log = getLog(EqualsParamBehaviour.class);

    @Override
    public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
        boolean equals = false;

        if(parameter != null) {
            Parameter comparisonParameter = message.getContextValue(new ContextLocation(conditionValue));

            if(comparisonParameter != null) {
                ParameterType<?> parameterType = parameter.getType();
                equals = parameter.getValue().equals(parameterType.convert(comparisonParameter.getValue()));
            }
        }

        log.debug("Parameter " + (equals ? "equals" : "does not equal") + " parameter '{}'", conditionValue);
        return equals == expectation;
    }
}
