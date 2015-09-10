package com.emarte.regurgitator.core;

final class CreateResponse extends Identifiable implements Step {
    private final Log log = Log.getLog(this);
	private final ContextLocation location;
	private final String staticValue;

	CreateResponse(String id, ContextLocation location, String staticValue) {
        super(id);
		this.location = location;
		this.staticValue = staticValue;
	}

    @Override
    public void execute(Message message) throws RegurgitatorException {
		Object value;

		if(location != null) {
			Parameter parameter = message.getContextValue(location);

			if(parameter == null) {
				throw new RegurgitatorException("No value found at context location '" + location + "'");
			}

			log.debug("Retrieved value from context location '" + location + "'");
			value = parameter.getValue();
		} else {
			log.debug("Using static value");
			value = staticValue;
		}

		message.getResponseCallback().respond(message, value);
    }
}
