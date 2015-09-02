package com.emarte.regurgitator.core;

public class ContainsBehaviour implements ConditionBehaviour {
	private Log log = Log.getLog(ContainsBehaviour.class);

	@Override
	public boolean evaluate(ContextLocation location, Message message, String conditionValue, boolean expectation) {
		boolean contains = false;
		Parameter parameter = message.getContextValue(location);

		if(parameter != null) {
			ParameterType parameterType = parameter.getType();
			contains = parameterType.contains(parameter.getValue(), parameterType.convert(conditionValue));
		}

		log.debug("Parameter '" + location + (contains ?  "' contain"  : "' does not contain") + " value '" + conditionValue + "'");
		return contains == expectation;
	}

}
