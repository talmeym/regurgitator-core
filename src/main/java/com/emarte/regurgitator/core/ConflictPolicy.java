package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

public enum ConflictPolicy {
    LEAVE {
        @Override
        public Object resolveConflict(Object existingValue, Object newValue, ParameterType type) {
            type.validate(existingValue);
            type.validate(newValue);
            log.debug("Keeping existing value '{}'", existingValue);
            return existingValue;
        }
    },
    REPLACE {
        @Override
        public Object resolveConflict(Object existingValue, Object newValue, ParameterType type) {
            type.validate(existingValue);
            type.validate(newValue);
            log.debug("Replacing '{}' with '{}'", existingValue, newValue);
            return newValue;
        }
    },
    CONCAT {
        @Override
        @SuppressWarnings("unchecked")
        public Object resolveConflict(Object existingValue, Object newValue, ParameterType type) {
            type.validate(existingValue);
            type.validate(newValue);
            log.debug("Adding '{}' to '{}'", newValue, existingValue);
            return type.concat(existingValue, newValue);
        }
    },
    REMOVE {
        @Override
        @SuppressWarnings("unchecked")
        public Object resolveConflict(Object existingValue, Object newValue, ParameterType type) {
            type.validate(existingValue);
            type.validate(newValue);
            log.debug("Removing '{}' from '{}'", newValue, existingValue);
            return type.remove(existingValue, newValue);
        }
    };

    private static final Log log = getLog(ConflictPolicy.class);

    abstract Object resolveConflict(Object existingValue, Object newValue, ParameterType type);
}
