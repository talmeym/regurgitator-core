package com.emarte.regurgitator.core;

final class Condition extends Identifiable {
	protected final Log log = Log.getLog(getClass());

	private final ContextLocation location;
	private final String conditionValue;
	private final boolean expectation;
	private final ConditionBehaviour behaviour;

	Condition(Object id, ContextLocation location, String conditionValue, boolean expectation, ConditionBehaviour behaviour) {
		super(id);
		this.location = location;
		this.conditionValue = conditionValue;
		this.expectation = expectation;
		this.behaviour = behaviour;
	}

	public boolean isMet(Message message) throws RegurgitatorException {
		log.debug("Evaluating condition '" + getId() + "'");
		return behaviour.evaluate(location, message, conditionValue, expectation);
	}
}