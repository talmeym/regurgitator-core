/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.List;

import static com.emarte.regurgitator.core.Log.getLog;

public final class GenerateParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueGenerator generator;

    public GenerateParameter(Object id, ParameterPrototype prototype, String context, ValueGenerator generator, List<ValueProcessor> processors) {
        super(id, prototype, context, processors);
        this.generator = generator;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        Object value = generator.generate();
        log.debug("Generated value '{}' for parameter '{}'", value, getPrototype().getName());
        return value;
    }
}
