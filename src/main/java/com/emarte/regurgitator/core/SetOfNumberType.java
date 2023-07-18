/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static com.emarte.regurgitator.core.CoreTypes.NUMBER;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static java.util.Collections.singletonList;

public final class SetOfNumberType extends AbstractCollectionType<Long, Set<Long>> {
    SetOfNumberType() {
        super(NUMBER);
    }

    @Override
    public Set<Long> createNew() {
        return new LinkedHashSet<Long>();
    }

    @Override
    public boolean validate(Object value) {
        return value instanceof Set && validateCollection((Collection<?>) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Long> convert(Object value) {
        if (validate(value)) {
            return (Set<Long>) value;
        }

        if (value instanceof Collection) {
            return fromCollection((Collection<?>) value);
        }

        if (STRING.validate(value)) {
            return STRING.toCollectionOf((String) value, createNew(), NUMBER);
        }

        return new LinkedHashSet<Long>(singletonList(NUMBER.convert(value)));
    }
}
