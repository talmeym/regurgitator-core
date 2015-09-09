package com.emarte.regurgitator.core;

final class BuildParameter extends ParameterExtractor {
    private static final Log log = Log.getLog(BuildParameter.class);

    private final ValueBuilder valueBuilder;

    BuildParameter(Object id, ParameterPrototype prototype, String context, ValueBuilder builder, ValueProcessor processor) {
        super(id, prototype, context, processor);
        this.valueBuilder = builder;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        log.debug("Building parameter value");
        Object value = valueBuilder.build(message);

        log.debug("Built value for parameter '" + getPrototype().getName() + "'");
        return value;
    }
}
