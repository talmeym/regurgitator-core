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
        Object value = valueSource.getValue(message, log);

        log.debug("Finding " + (last ? "last " : "") + "index of '{}' in value '{}'", value, collection);
        int index = 0, lastIndex = -1;

        for(Object object: collection) {
            if(object.equals(value)) {
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
