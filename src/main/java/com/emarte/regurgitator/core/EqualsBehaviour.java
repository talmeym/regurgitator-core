package com.emarte.regurgitator.core;

public class EqualsBehaviour implements ConditionBehaviour {
	private static Log log = Log.getLog(EqualsBehaviour.class);

	@Override
	public boolean evaluate(ContextLocation location, Message message, String conditionValue, boolean expectation) {
		boolean equals = false;
		Parameter parameter = message.getContextValue(location);

		if(parameter != null) {
			ParameterType parameterType = parameter.getType();
			equals = parameter.getValue().equals(parameterType.convert(conditionValue));
		}

		log.debug("Parameter '" + location + (equals ? "' equals" : "' does not equal") + " value '" + conditionValue + "'");
		return equals == expectation;
	}
}
