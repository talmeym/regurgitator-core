package com.emarte.regurgitator.core;

import java.util.*;

public final class Parameter implements HasId {
    private Log log = Log.getLog(getClass());
    private final ParameterPrototype prototype;
    private Object value;

    public Parameter(ParameterPrototype prototype, Object value) {
        prototype.validateValue(value);
        this.prototype = prototype;
        this.value = value;
    }

    @Override
    public Object getId() {
        return getName();
    }

    public String getName() {
        return prototype.getName();
    }

    public ParameterType getType() {
        return prototype.getType();
    }

    public ConflictPolicy getConflictPolicy() {
        return prototype.getConflictPolicy();
    }

    public Object getValue() {
		if(value instanceof Collection) {
			if (value instanceof List) {
				return Collections.unmodifiableList((List) value);
			}

			if (value instanceof Set) {
				return Collections.unmodifiableSet((Set) value);
			}
		}

        return value;
    }

	void merge(Parameter parameter) {
		Object newValue = getType().convert(parameter.getValue());
		value = parameter.getConflictPolicy().resolveConflict(value, newValue, getType());
		log.debug("Merged resulted in value '" + newValue + "'");
	}
}
