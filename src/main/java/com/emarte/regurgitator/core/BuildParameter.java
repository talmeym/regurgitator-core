package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.Log.getLog;

final class BuildParameter extends ParameterExtractor {
    private final Log log = getLog(this);
    private final ValueBuilder valueBuilder;

    BuildParameter(Object id, ParameterPrototype prototype, String context, ValueBuilder builder, ValueProcessor processor) {
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
