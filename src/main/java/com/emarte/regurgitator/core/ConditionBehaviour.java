package com.emarte.regurgitator.core;

public interface ConditionBehaviour {
	public boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) throws RegurgitatorException;
}