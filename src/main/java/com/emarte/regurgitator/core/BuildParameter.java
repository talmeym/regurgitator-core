/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

public final class BuildParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueBuilder valueBuilder;

    public BuildParameter(Object id, ParameterPrototype prototype, String context, ValueBuilder builder, ValueProcessor processor) {
        super(id, prototype, context, processor);
        this.valueBuilder = builder;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        log.debug("Building parameter value");
        Object value = valueBuilder.build(message);

        log.debug("Built value for parameter '{}'", getPrototype().getName());
        return value;
    }
}
