/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;

/**
 * Abstract base class for all collection types, e.g. LIST_OF_STRING
 * @param <INNER> The java type that the underlying java collection will hold, e.g. String
 * @param <TYPE> The underlying java collection (including INNER), e.g. List<String>
 */
public abstract class AbstractCollectionType<INNER, TYPE extends Collection<INNER>> implements ParameterType<TYPE> {
    private final ParameterType<INNER> type;

    /**
     * Constructor
     * @param type ParameterType for this collections INNER java type, e.g. ParameterType<String>
     */
    public AbstractCollectionType(ParameterType<INNER> type) {
        this.type = type;
    }

    /**
     * Concatenates two collections together
     * @see ParameterType#concat(Object, Object)
     * @param prefix The prefix collection to be concatenated
     * @param suffix The suffix collection to be concatenated
     * @return A new java collection of type <TYPE>, containing both the inputted collections
     */
    @Override
    public TYPE concat(TYPE prefix, TYPE suffix) {
        TYPE result = createNew();
        result.addAll(prefix);
        result.addAll(suffix);
        return result;
    }

    /**
     * Removes the contents of one collection from another
     * @see ParameterType#remove(Object, Object)
     * @param existingValue The exiting collection
     * @param newValue The new collection to remove from it
     * @return A new java collection of type <TYPE>, containing the existing collection, minus the contents of the new
     */
    @Override
    public TYPE remove(TYPE existingValue, TYPE newValue) {
        TYPE result = createNew();
        result.addAll(existingValue);
        result.removeAll(newValue);
        return result;
    }

    /**
     * Validates that the contents of a collection of unknown type
     * @param value The collection of unknown type
     * @return Whether all elements of the collection are of type <INNER>
     */
    public boolean validateCollection(Collection<?> value) {
        for(Object obj: value) {
            if(!type.validate(obj)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Convert to this collection type from another collection
     * @param collection The collection of unknown type
     * @return A collection of type <TYPE>
     */
    @Override
    public TYPE fromCollection(Collection<?> collection) {
        TYPE value = createNew();

        for(Object obj : collection) {
            value.add(type.convert(obj));
        }

        return value;
    }

    /**
     * Returns whether this collection contains (all elements of) another collection
     * @param container the base collection
     * @param contained the collection being checked for
     * @return Whether the collection is found
     */
    @Override
    public boolean contains(TYPE container, TYPE contained) {
        return container.containsAll(contained);
    }
}
