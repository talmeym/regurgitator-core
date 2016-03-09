package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

final class EqualsBehaviour implements ConditionBehaviour {
	private static final Log log = getLog(EqualsBehaviour.class);

	@Override
	public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
		boolean equals = false;

		if(parameter != null) {
			ParameterType parameterType = parameter.getType();
			equals = parameter.getValue().equals(parameterType.convert(conditionValue));
		}

		log.debug("Parameter " + (equals ? "equals" : "does not equal") + " value '" + conditionValue + "'");
		return equals == expectation;
	}
}
