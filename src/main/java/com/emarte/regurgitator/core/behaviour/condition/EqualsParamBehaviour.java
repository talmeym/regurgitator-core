package com.emarte.regurgitator.core.behaviour.condition;

import com.emarte.regurgitator.core.*;

public class EqualsParamBehaviour implements ConditionBehaviour {
	private static Log log = Log.getLog(EqualsParamBehaviour.class);

	@Override
	public boolean evaluate(ContextLocation location, Message message, String conditionValue, boolean expectation) {
		boolean equals = false;
		Parameter parameter = message.getContextValue(location);

		if(parameter != null) {
			Parameter comparisonParameter = message.getContextValue(new ContextLocation(conditionValue));

			if(comparisonParameter != null) {
				equals = parameter.getValue().equals(parameter.getType().convert(comparisonParameter.getValue()));
			}

		}

		log.debug("Parameter '" + location + (equals ? "' equals" : "' does not equal") + " parameter '" + conditionValue + "'");
		return equals == expectation;
	}
}
