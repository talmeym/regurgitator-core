/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;

public abstract class AbstractCollectionType<INNER, TYPE extends Collection<INNER>> implements ParameterType<TYPE> {
    private final ParameterType<INNER> type;

    public AbstractCollectionType(ParameterType<INNER> type) {
        this.type = type;
    }

    @Override
    public TYPE concat(TYPE prefix, TYPE suffix) {
        TYPE result = createNew();
        result.addAll(prefix);
        result.addAll(suffix);
        return result;
    }

    @Override
    public TYPE remove(TYPE existingValue, TYPE newValue) {
        TYPE result = createNew();
        result.addAll(existingValue);
        result.removeAll(newValue);
        return result;
    }

    public boolean validateCollection(Collection<?> value) {
        for(Object obj: value) {
            if(!type.validate(obj)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public <OTHER, COLLECTION extends Collection<OTHER>> COLLECTION toCollectionOf(TYPE value, COLLECTION collection, ParameterType<OTHER> type) {
        for(Object obj : value) {
            collection.add(type.convert(obj));
        }

        return collection;
    }

    @Override
    public TYPE fromCollection(Collection<?> collection) {
        TYPE value = createNew();

        for(Object obj : collection) {
            value.add(type.convert(obj));
        }

        return value;
    }

    @Override
    public boolean contains(TYPE container, TYPE contained) {
        return container.containsAll(contained);
    }
}
