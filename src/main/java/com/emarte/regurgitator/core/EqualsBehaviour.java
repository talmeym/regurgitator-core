package com.emarte.regurgitator.core;

public class EqualsBehaviour implements ConditionBehaviour {
	private static Log log = Log.getLog(EqualsBehaviour.class);

	@Override
	public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
		boolean equals = false;

		if(parameter != null) {
			ParameterType parameterType = parameter.getType();
			equals = parameter.getValue().equals(parameterType.convert(conditionValue));
		}

		log.debug("Parameter '" + parameter + (equals ? "' equals" : "' does not equal") + " value '" + conditionValue + "'");
		return equals == expectation;
	}
}
