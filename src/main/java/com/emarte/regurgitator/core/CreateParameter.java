package com.emarte.regurgitator.core;

final class CreateParameter extends ParameterExtractor {
	private final Log log = Log.getLog(this);
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

		if (source != null) {
			Parameter parameter = message.getContextValue(source);

			if (parameter != null) {
				log.debug("Retrieved value from context location '" + source + "'");
				value = parameter.getValue();
			} else if (staticValue != null) {
				log.debug("defaulting to static value '" + staticValue + "'");
				value = staticValue;
			} else {
				throw new RegurgitatorException("No value found at context location '" + source + "'");
			}
		} else {
			log.debug("Using static value '" + staticValue + "'");
			value = staticValue;
		}

		return value;
	}
}
