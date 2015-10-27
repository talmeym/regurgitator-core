package com.emarte.regurgitator.core;

final class CreateResponse extends Identifiable implements Step {
    private final Log log = Log.getLog(this);
	private final ContextLocation source;
	private final String staticValue;
	private final ValueProcessor valueProcessor;

	CreateResponse(String id, ContextLocation source, String staticValue, ValueProcessor valueProcessor) {
        super(id);
		this.source = source;
		this.staticValue = staticValue;
		this.valueProcessor = valueProcessor;
	}

    @Override
    public void execute(Message message) throws RegurgitatorException {
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

		if(valueProcessor != null) {
			value = valueProcessor.process(value, message);
		}

		message.getResponseCallback().respond(message, value);
    }
}
