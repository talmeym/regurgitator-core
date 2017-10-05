package com.emarte.regurgitator.core;

import java.util.Collection;

public interface ParameterType<TYPE> {
    TYPE createNew();

    TYPE concat(TYPE prefix, TYPE suffix);

    TYPE remove(TYPE existingValue, TYPE newValue);

    boolean validate(Object value);

    TYPE convert(Object value);

    boolean contains(TYPE container, TYPE value);

    <OTHER, COLLECTION extends Collection<OTHER>> COLLECTION toCollectionOf(TYPE value, COLLECTION collection, ParameterType<OTHER> type);

    TYPE fromCollection(Collection collection);
}
