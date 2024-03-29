/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import static uk.emarte.regurgitator.core.Log.getLog;

public final class Condition extends Identifiable {
    private final Log log = getLog(this);
    private final ContextLocation location;
    private final String conditionValue;
    private final boolean expectation;
    private final ConditionBehaviour behaviour;

    public Condition(Object id, ContextLocation location, String conditionValue, boolean expectation, ConditionBehaviour behaviour) {
        super(id);
        this.location = location;
        this.conditionValue = conditionValue;
        this.expectation = expectation;
        this.behaviour = behaviour;
    }

    boolean isMet(Message message) throws RegurgitatorException {
        Parameter parameter = message.getContextValue(location);
        log.debug("Evaluating parameter '{}'", location);
        return behaviour.evaluate(parameter, message, conditionValue, expectation);
    }
}