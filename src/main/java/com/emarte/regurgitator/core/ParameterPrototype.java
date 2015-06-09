package com.emarte.regurgitator.core;

public final class ParameterPrototype {
    private final String name;
    private final ParameterType type;
    private final ConflictPolicy conflictPolicy;

    public ParameterPrototype(String name, ParameterType type, ConflictPolicy conflictPolicy) {
        this.name = name;
        this.type = type;
        this.conflictPolicy = conflictPolicy;
    }

    public String getName() {
        return name;
    }

    public ParameterType getType() {
        return type;
    }

    public ConflictPolicy getConflictPolicy() {
        return conflictPolicy;
    }

    public void validateValue(Object value) {
        if (!type.validate(value)) {
            throw new IllegalArgumentException("Invalid type: name=" + getName() + " type=" + type.getName() + ", argClass=" + value.getClass().getName());
        }
    }
}
