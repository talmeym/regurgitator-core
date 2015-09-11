package com.emarte.regurgitator.core;

public class EqualsParamBehaviour implements ConditionBehaviour {
	private static Log log = Log.getLog(EqualsParamBehaviour.class);

	@Override
	public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
		boolean equals = false;

		if(parameter != null) {
			Parameter comparisonParameter = message.getContextValue(new ContextLocation(conditionValue));

			if(comparisonParameter != null) {
				ParameterType parameterType = parameter.getType();
				equals = parameter.getValue().equals(parameterType.convert(comparisonParameter.getValue()));
			}

		}

		log.debug("Parameter " + (equals ? "equals" : "does not equal") + " parameter '" + conditionValue + "'");
		return equals == expectation;
	}
}