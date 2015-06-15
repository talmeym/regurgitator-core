package com.emarte.regurgitator.core;

final class BuildParameter extends ParameterExtractor {
    private static final Log log = Log.getLog(BuildParameter.class);

    private final ValueBuilder valueBuilder;
    private final ValueValidator valueValidator;

    BuildParameter(Object id, ParameterPrototype prototype, String context, ValueBuilder valueBuilder, ValueValidator valueValidator) {
        super(id, prototype, context);
        this.valueBuilder = valueBuilder;
        this.valueValidator = valueValidator;
    }

    @Override
    public Object extractValue(Message message) throws RegurgitatorException {
        log.debug("Building parameter value");
        Object value = valueBuilder.build(message);

        if(valueValidator != null) {
            log.debug("Validating built parameter value");
            if(!valueValidator.validate(value)) {
				throw new RegurgitatorException("Error validating message");
			}
        }

        log.debug("Built value for parameter '" + getPrototype().getName() + "'");
        return value;
    }
}
