package com.emarte.regurgitator.core.behaviour.condition;

import com.emarte.regurgitator.core.*;

public class ExistsBehaviour implements ConditionBehaviour {
	private Log log = Log.getLog(ExistsBehaviour.class);

	@Override
	public Object getId() {
		return "EXISTS";
	}

	@Override
	public boolean evaluate(ContextLocation location, Message message, String conditionValue, boolean notUsed) {
		Boolean expectation = Boolean.valueOf(conditionValue);
		Parameter parameter = message.getContextValue(location);
		boolean exists = parameter != null;
		log.debug("Parameter '" + location + (exists ? "' exists" : "' does not exist"));
		return exists == expectation;
	}
}
