/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import static java.util.Collections.unmodifiableList;
import static java.util.Collections.unmodifiableSet;
import static uk.emarte.regurgitator.core.Log.getLog;

public final class Parameter implements HasId {
    private static final Log log = getLog(Parameter.class);

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

    public ParameterType<?> getType() {
        return prototype.getType();
    }

    public ConflictPolicy getConflictPolicy() {
        return prototype.getConflictPolicy();
    }

    public Object getValue() {
        if(value instanceof Collection) {
            if (value instanceof List) {
                return unmodifiableList((List<?>) value);
            }

            if (value instanceof Set) {
                return unmodifiableSet((Set<?>) value);
            }
        }

        return value;
    }

    void merge(Parameter parameter) {
        value = parameter.getConflictPolicy().resolveConflict(value, parameter.getValue(), getType());
        log.debug("Merge resulted in value '{}'", value);
    }
}
