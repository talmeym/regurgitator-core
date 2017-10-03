package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

final class ContainsBehaviour implements ConditionBehaviour {
	private static final Log log = getLog(ContainsBehaviour.class);

	@Override
	@SuppressWarnings("unchecked")
	public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) {
		boolean contains = false;

		if(parameter != null) {
			ParameterType parameterType = parameter.getType();
			contains = parameterType.contains(parameter.getValue(), parameterType.convert(conditionValue));
		}

		log.debug("Parameter " + (contains ?  "contain"  : "does not contain") + " value '" + conditionValue + "'");
		return contains == expectation;
	}
}
