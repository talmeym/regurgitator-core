package com.emarte.regurgitator.core;

public interface ConditionBehaviour {
	boolean evaluate(Parameter parameter, Message message, String conditionValue, boolean expectation) throws RegurgitatorException;
}