/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.emarte.regurgitator.core.CoreTypes.DECIMAL;
import static com.emarte.regurgitator.core.CoreTypes.STRING;
import static java.util.Collections.singletonList;

public final class ListOfDecimalType extends AbstractCollectionType<Double, List<Double>> {
    ListOfDecimalType() {
        super(DECIMAL);
    }

    @Override
    public List<Double> createNew() {
        return new ArrayList<Double>();
    }

    @Override
    public boolean validate(Object value) {
        return value instanceof List && validateCollection((Collection) value);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Double> convert(Object value) {
        if (validate(value)) {
            return (List<Double>) value;
        }

        if (value instanceof Collection) {
            return fromCollection((Collection) value);
        }

        if (STRING.validate(value)) {
            return STRING.toCollectionOf((String) value, createNew(), DECIMAL);
        }

        return singletonList(DECIMAL.convert(value));
    }
}
