package com.emarte.regurgitator.core;

public final class CreateResponse extends Identifiable implements Step {
    private final Log log = Log.getLog(this);
	private final ValueSource valueSource;
	private final ValueProcessor valueProcessor;

	CreateResponse(String id, ValueSource valueSource, ValueProcessor valueProcessor) {
        super(id);
		this.valueSource = valueSource;
		this.valueProcessor = valueProcessor;
	}

    @Override
    public void execute(Message message) throws RegurgitatorException {
		execute(message, log);
    }

	public void execute(Message message, Log log) throws RegurgitatorException {
		Object value = valueSource.getValue(message, log);

		if(valueProcessor != null) {
			value = valueProcessor.process(value, message);
		}

		log.debug("Sending response to callback");
		message.getResponseCallback().respond(message, value);
	}
}
