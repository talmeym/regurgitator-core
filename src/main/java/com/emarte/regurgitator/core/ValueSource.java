package com.emarte.regurgitator.core;

public class ValueSource {

	private final ContextLocation source;
	private final String staticValue;

	public ValueSource(ContextLocation source, String staticValue) {
		this.source = source;
		this.staticValue = staticValue;
	}

	public Object getValue(Message message, Log log) throws RegurgitatorException {
		if (source != null) {
			Parameter parameter = message.getContextValue(source);

			if (parameter != null) {
				log.debug("Retrieved value from context location '" + source + "'");
				return parameter.getValue();
			} else if (staticValue != null) {
				log.debug("defaulting to static value '" + staticValue + "'");
				return staticValue;
			}

			throw new RegurgitatorException("No value found at context location '" + source + "'");
		}

		log.debug("Using static value '" + staticValue + "'");
		return staticValue;
	}
}
