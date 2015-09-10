package com.emarte.regurgitator.core;

final class CreateResponse extends Identifiable implements Step {
    private final Log log = Log.getLog(this);
	private final ContextLocation source;
	private final String staticValue;

	CreateResponse(String id, ContextLocation source, String staticValue) {
        super(id);
		this.source = source;
		this.staticValue = staticValue;
	}

    @Override
    public void execute(Message message) throws RegurgitatorException {
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

		message.getResponseCallback().respond(message, value);
    }
}
