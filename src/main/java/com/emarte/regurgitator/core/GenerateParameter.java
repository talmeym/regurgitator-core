package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

final class GenerateParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueGenerator generator;

    GenerateParameter(Object id, ParameterPrototype prototype, String context, ValueGenerator generator, ValueProcessor processor) {
        super(id, prototype, context, processor);
        this.generator = generator;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        Object value = generator.generate();
        log.debug("Generated value '" + value + "' for parameter '" + getPrototype().getName() + '\'');
        return value;
    }
}
