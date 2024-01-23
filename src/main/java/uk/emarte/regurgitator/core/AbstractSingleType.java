/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;

public abstract class AbstractSingleType<TYPE> implements ParameterType<TYPE> {
    @Override
    public TYPE fromCollection(Collection<?> collection) {
        TYPE total = createNew();

        for (Object obj : collection) {
            total = concat(total, convert(obj));
        }

        return total;
    }
}
