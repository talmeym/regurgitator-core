/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

public final class GenerateParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueGenerator generator;

    public GenerateParameter(Object id, ParameterPrototype prototype, String context, ValueGenerator generator, ValueProcessor processor) {
        super(id, prototype, context, processor);
        this.generator = generator;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        Object value = generator.generate();
        log.debug("Generated value '{}' for parameter '{}'", value, getPrototype().getName());
        return value;
    }
}
