/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Collections.singletonList;
import static uk.emarte.regurgitator.core.CoreTypes.NUMBER;
import static uk.emarte.regurgitator.core.CoreTypes.STRING;

public final class ListOfNumberType extends AbstractCollectionType<Long, List<Long>> {
    ListOfNumberType() {
        super(NUMBER);
    }

    @Override
    public List<Long> createNew() {
        return new ArrayList<Long>();
    }

    @Override
    public boolean validate(Object value) {
        return value instanceof List && validateCollection((Collection<?>) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Long> convert(Object value) {
        if (validate(value)) {
            return (List<Long>) value;
        }

        if (value instanceof Collection) {
            return fromCollection((Collection<?>) value);
        }

        if (STRING.validate(value)) {
            return STRING.toCollectionOf((String) value, createNew(), NUMBER);
        }

        return singletonList(NUMBER.convert(value));
    }
}
