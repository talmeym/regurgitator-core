/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import static uk.emarte.regurgitator.core.Log.getLog;

public enum ConflictPolicy {
    LEAVE {
        @Override
        public <TYPE> Object resolveConflict(Object existingValue, Object newValue, ParameterType<TYPE> type) {
            warnIfDoesNotValidate(existingValue, type);
            warnIfDoesNotValidate(newValue, type);
            log.debug("Keeping existing value '{}'", existingValue);
            return existingValue;
        }
    },
    REPLACE {
        @Override
        public <TYPE> Object resolveConflict(Object existingValue, Object newValue, ParameterType<TYPE> type) {
            warnIfDoesNotValidate(existingValue, type);
            warnIfDoesNotValidate(newValue, type);
            log.debug("Replacing '{}' with '{}'", existingValue, newValue);
            return newValue;
        }
    },
    CONCAT {
        @Override
        public <TYPE> Object resolveConflict(Object existingValue, Object newValue, ParameterType<TYPE> type) {
            warnIfDoesNotValidate(existingValue, type);
            warnIfDoesNotValidate(newValue, type);
            log.debug("Adding '{}' to '{}'", newValue, existingValue);
            return type.concat(type.convert(existingValue), type.convert(newValue));
        }
    },
    REMOVE {
        @Override
        public <TYPE> Object resolveConflict(Object existingValue, Object newValue, ParameterType<TYPE> type) {
            warnIfDoesNotValidate(existingValue, type);
            warnIfDoesNotValidate(newValue, type);
            log.debug("Removing '{}' from '{}'", newValue, existingValue);
            return type.remove(type.convert(existingValue), type.convert(newValue));
        }
    };

    private static final Log log = getLog(ConflictPolicy.class);

    abstract <TYPE> Object resolveConflict(Object existingValue, Object newValue, ParameterType<TYPE> type);

    private static void warnIfDoesNotValidate(Object value, ParameterType<?> parameterType) {
        if(!parameterType.validate(value))
            log.warn("Value '" + value + "' does not validate as parameter type " + parameterType.getClass().getSimpleName());
    }
}
