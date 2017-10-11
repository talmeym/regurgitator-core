/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.Collection;

import static com.emarte.regurgitator.core.Log.getLog;

public final class IndexOfProcessor extends CollectionProcessor {
    private static final Log log = getLog(IndexOfProcessor.class);
    private final ValueSource valueSource;
    private final boolean last;

    public IndexOfProcessor(ValueSource valueSource, boolean last) {
        this.valueSource = valueSource;
        this.last = last;
    }

    @Override
    public Object processCollection(Collection collection, Message message) throws RegurgitatorException {
        Object valueToFind = valueSource.getValue(message, log);

        log.debug("Finding " + (last ? "last " : "") + "index of '{}' in value '{}'", valueToFind, collection);
        long index = 0L, lastIndex = -1L;

        for(Object object: collection) {
            if(object.equals(valueToFind)) {
                if(!last) {
                    return index;
                }

                lastIndex = index;
            }

            index++;
        }

        return lastIndex;
    }
}
