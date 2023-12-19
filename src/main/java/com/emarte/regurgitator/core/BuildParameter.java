/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.List;

import static com.emarte.regurgitator.core.Log.getLog;

public final class BuildParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueBuilder builder;

    public BuildParameter(Object id, ParameterPrototype prototype, String context, ValueBuilder builder, List<ValueProcessor> processors) {
        super(id, prototype, context, processors);
        this.builder = builder;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        log.debug("Building parameter value");
        Object value = builder.build(message);

        log.debug("Built value for parameter '{}'", prototype.getName());
        return value;
    }
}
