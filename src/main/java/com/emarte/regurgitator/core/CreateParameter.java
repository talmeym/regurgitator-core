package com.emarte.regurgitator.core;

final class CreateParameter extends ParameterExtractor  {
    private final Log log = Log.getLog(getClass());

    private final ContextLocation source;
    private final String staticValue;

    CreateParameter(Object id, ParameterPrototype prototype, String context, ContextLocation source, String staticValue, ValueProcessor processor) {
        super(id, prototype, context, processor);
        this.source = source;
        this.staticValue = staticValue;
    }

	@Override
	public Object extractValue(Message message) throws RegurgitatorException {
		Object value;

		if(source != null) {
			Parameter parameter = message.getContextValue(source);

			if(parameter == null) {
				throw new RegurgitatorException("No value found at context location '" + source + "'");
			}

			log.debug("Retrieved value from context location '" + source + "'");
			value = parameter.getValue();
		} else {
			log.debug("Using static value");
			value = staticValue;
		}

		return value;
	}
}
