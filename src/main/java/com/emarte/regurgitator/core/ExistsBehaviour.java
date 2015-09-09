package com.emarte.regurgitator.core;

public class ExistsBehaviour implements ConditionBehaviour {
	private Log log = Log.getLog(ExistsBehaviour.class);

	@Override
	public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean notUsed) {
		Boolean expectation = Boolean.valueOf(conditionValue);
		boolean exists = parameter != null;
		log.debug("Parameter '" + parameter + (exists ? "' exists" : "' does not exist"));
		return exists == expectation;
	}
}
