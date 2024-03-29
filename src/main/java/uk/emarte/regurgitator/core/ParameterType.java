/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;

public interface ParameterType<TYPE> {
    TYPE createNew();

    TYPE concat(TYPE prefix, TYPE suffix);

    TYPE remove(TYPE existingValue, TYPE newValue);

    boolean validate(Object value);

    TYPE convert(Object value);

    boolean contains(TYPE container, TYPE contained);

    TYPE fromCollection(Collection<?> collection);
}
