/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.Collection;

import static com.emarte.regurgitator.core.Log.getLog;
import static com.emarte.regurgitator.core.StringType.stringify;
import static java.lang.Integer.parseInt;

public final class AtIndexProcessor extends CollectionProcessor {
    private static final Log log = getLog(AtIndexProcessor.class);
    private final ValueSource indexSource;

    public AtIndexProcessor(ValueSource indexSource) {
        this.indexSource = indexSource;
    }

    @Override
    public Object processCollection(Collection<?> collection, Message message) throws RegurgitatorException {
        Object indexObj = indexSource.getValue(message, log);

        int index = parseInt(stringify(indexObj)), i = 0;
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
