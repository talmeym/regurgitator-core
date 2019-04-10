/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static java.util.Collections.singletonList;

public final class ListOfStringType extends AbstractCollectionType<String, List<String>> {
    ListOfStringType() {
        super(STRING);
    }

    @Override
    public List<String> createNew() {
        return new ArrayList<String>();
    }

    @Override
    public boolean validate(Object value) {
        return value instanceof List && validateCollection((Collection) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<String> convert(Object value) {
        if (validate(value)) {
            return (List<String>) value;
        }

        if (value instanceof Collection) {
            return fromCollection((Collection) value);
        }

        if (STRING.validate(value)) {
            return STRING.toCollectionOf((String) value, createNew(), STRING);
        }

        return singletonList(STRING.convert(value));
    }
}
