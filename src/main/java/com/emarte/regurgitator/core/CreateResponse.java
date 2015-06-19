package com.emarte.regurgitator.core;

final class CreateResponse extends Identifiable implements Step {
    private static final Log log = Log.getLog(CreateResponse.class);

	private final ContextLocation location;
	private final String staticValue;
	private final ValueProcessor processor;

	CreateResponse(String id, ContextLocation location, String staticValue, ValueProcessor processor) {
        super(id);
		this.location = location;
		this.staticValue = staticValue;
		this.processor = processor;
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

		if(processor != null) {
			log.debug("Processing value");
			value = processor.process(value);
		}

		message.getResponseCallback().respond(message, value);
    }
}
