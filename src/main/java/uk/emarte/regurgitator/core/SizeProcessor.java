/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Collection;

import static uk.emarte.regurgitator.core.Log.getLog;

public final class SizeProcessor extends CollectionProcessor {
    private static final Log log = getLog(SizeProcessor.class);
    private final boolean asIndex;

    public SizeProcessor(boolean asIndex) {
        this.asIndex = asIndex;
    }

    @Override
    public Object processCollection(Collection<?> collection, Message message) {
        log.debug("Returning size of '{}'" + (asIndex ? " as last index" : ""), collection);
        return asIndex ? collection.size() - 1 : collection.size();
    }
}
