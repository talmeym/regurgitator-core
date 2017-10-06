/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.Collection;

public abstract class CollectionProcessor implements ValueProcessor {
    @Override
    public final Object process(Object value, Message message) throws RegurgitatorException {
        if(!(value instanceof Collection)) {
            throw new RegurgitatorException("Parameter is not a collection");
        }

        return processCollection((Collection) value, message);
    }

    public abstract Object processCollection(Collection collection, Message message) throws RegurgitatorException;
}
