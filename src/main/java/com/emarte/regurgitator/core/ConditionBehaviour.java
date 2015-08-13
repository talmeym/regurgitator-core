package com.emarte.regurgitator.core;

public interface ConditionBehaviour extends HasId {
	public boolean evaluate(ContextLocation location, Message message, String conditionValue, boolean expectation) throws RegurgitatorException;
}