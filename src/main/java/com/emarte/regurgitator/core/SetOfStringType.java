/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static java.util.Collections.singletonList;

public final class SetOfStringType extends AbstractCollectionType<String, Set<String>> {
    SetOfStringType() {
        super(STRING);
    }

    @Override
    public Set<String> createNew() {
        return new LinkedHashSet<String>();
    }

    @Override
    public boolean validate(Object value) {
        return value instanceof Set && validateCollection((Collection) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<String> convert(Object value) {
        if (validate(value)) {
            return (Set<String>) value;
        }

        if (value instanceof Collection) {
            return fromCollection((Collection) value);
        }

        if (STRING.validate(value)) {
            return STRING.toCollectionOf((String) value, createNew(), STRING);
        }

        return new LinkedHashSet<String>(singletonList(STRING.convert(value)));
    }
}
