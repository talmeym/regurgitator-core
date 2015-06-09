package com.emarte.regurgitator.core;

public enum ConflictPolicy {

    LEAVE {
        @Override
        public Object resolveConflict(Object existingValue, Object newValue, ParameterType type) {
            type.validate(existingValue);
            type.validate(newValue);
            return existingValue;
        }
    },
    REPLACE {
        @Override
        public Object resolveConflict(Object existingValue, Object newValue, ParameterType type) {
            type.validate(existingValue);
            type.validate(newValue);
            return newValue;
        }
    },
    CONCAT {
        @Override
        public Object resolveConflict(Object existingValue, Object newValue, ParameterType type) {
            type.validate(existingValue);
            type.validate(newValue);
            return type.concat(existingValue, newValue);
        }
    },
    REMOVE {
        @Override
        public Object resolveConflict(Object existingValue, Object newValue, ParameterType type) {
            type.validate(existingValue);
            type.validate(newValue);
            return type.remove(existingValue, newValue);
        }
    };

    abstract Object resolveConflict(Object existingValue, Object newValue, ParameterType type);
}
