/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.Collection;

import static com.emarte.regurgitator.core.CoreTypes.DECIMAL;
import static com.emarte.regurgitator.core.StringType.stringify;

public final class NumberType extends AbstractSingleType<Long> {
    @Override
    public Long createNew() {
        return 0L;
    }

    @Override
    public Long concat(Long prefix, Long suffix) {
        return prefix + suffix;
    }

    @Override
    public Long remove(Long existingValue, Long newValue) {
        return existingValue - newValue;
    }

    @Override
    public boolean validate(Object value) {
        return value instanceof Long;
    }

    @Override
    public Long convert(Object value) {
        if (validate(value)) {
            return (Long) value;
        }

        if (value instanceof Collection) {
            return fromCollection((Collection<?>) value);
        }

        if (DECIMAL.validate(value)) {
            return Math.round((Double) value);
        }

        return Long.parseLong(stringify(value));
    }

    @Override
    public boolean contains(Long container, Long contained) {
        return container >= contained;
    }
}
