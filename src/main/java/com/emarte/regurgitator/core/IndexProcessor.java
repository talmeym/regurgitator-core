/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.Collection;

import static com.emarte.regurgitator.core.Log.getLog;
import static com.emarte.regurgitator.core.StringType.stringify;
import static java.lang.Long.parseLong;

public final class IndexProcessor extends CollectionProcessor {
    private static final Log log = getLog(IndexProcessor.class);
    private final ValueSource valueSource;

    public IndexProcessor(ValueSource valueSource) {
        this.valueSource = valueSource;
    }

    @Override
    public Object processCollection(Collection collection, Message message) throws RegurgitatorException {
        Object valueToUse = valueSource.getValue(message, log);

        long index = parseLong(stringify(valueToUse)), i = 0L;
        log.debug("Finding index '{}' of value '{}'", index, collection);

        if(index < 0 || index >= collection.size()) {
            throw new RegurgitatorException("Invalid index: " + index);
        }

        for(Object object: collection) {
            if(i++ == index) {
                return object;
            }
        }

        throw new RegurgitatorException("Should never reach here");
    }
}
