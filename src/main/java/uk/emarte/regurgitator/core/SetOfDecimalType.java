/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Collections.singletonList;
import static uk.emarte.regurgitator.core.CoreTypes.DECIMAL;
import static uk.emarte.regurgitator.core.CoreTypes.STRING;

public final class SetOfDecimalType extends AbstractCollectionType<Double, Set<Double>> {
    SetOfDecimalType() {
        super(DECIMAL);
    }

    @Override
    public Set<Double> createNew() {
        return new LinkedHashSet<>();
    }

    @Override
    public boolean validate(Object value) {
        return value instanceof Set && validateCollection((Collection<?>) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<Double> convert(Object value) {
        if (validate(value)) {
            return (Set<Double>) value;
        }

        if (value instanceof Collection) {
            return fromCollection((Collection<?>) value);
        }

        if (STRING.validate(value)) {
            return STRING.toCollectionOf((String) value, createNew(), DECIMAL);
        }

        return new LinkedHashSet<>(singletonList(DECIMAL.convert(value)));
    }
}
