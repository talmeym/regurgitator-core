package com.emarte.regurgitator.core;

final class GenerateParameter extends ParameterExtractor {
    private static final Log log = Log.getLog(GenerateParameter.class);

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
