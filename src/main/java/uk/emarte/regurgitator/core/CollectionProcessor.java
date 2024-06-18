/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;

public abstract class CollectionProcessor implements ValueProcessor {
    private final Log log = Log.getLog(CollectionProcessor.class);

    @Override
    public final Object process(Object value, Message message) throws RegurgitatorException {
        if(value != null) {
            if(value instanceof Collection) {
                return processCollection((Collection<?>) value, message);
            }

            throw new RegurgitatorException("Value is not a collection");
        }

        log.warn("No value to process");
        return null;
    }

    public abstract Object processCollection(Collection<?> collection, Message message) throws RegurgitatorException;
}
