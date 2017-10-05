package com.emarte.regurgitator.core;

import java.util.Collection;

import static com.emarte.regurgitator.core.Log.getLog;

final class SizeProcessor extends CollectionProcessor {
    private static final Log log = getLog(SizeProcessor.class);
    private boolean asIndex;

    SizeProcessor(boolean asIndex) {
        this.asIndex = asIndex;
    }

    @Override
    public Object processCollection(Collection collection, Message message) {
        log.debug("Returning size of '{}'" + (asIndex ? " as last index" : ""), collection);
        return asIndex ? collection.size() - 1 : collection.size();
    }
}
