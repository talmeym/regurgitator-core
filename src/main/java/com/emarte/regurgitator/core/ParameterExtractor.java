package com.emarte.regurgitator.core;

public abstract class ParameterExtractor extends Identifiable implements Step {
    private final ParameterPrototype prototype;
	private final String context;

    public ParameterExtractor(Object id, ParameterPrototype prototype, String context) {
        super(id);
        this.prototype = prototype;
		this.context = context;
    }

    public ParameterPrototype getPrototype() {
        return prototype;
    }

	public String getContext() {
		return context;
	}

    @Override
    public final void execute(Message message) throws RegurgitatorException {
		Object value = prototype.getType().convert(extractValue(message));
		message.getContext(context).setValue(new Parameter(prototype, value));
    }

    public abstract Object extractValue(Message message) throws RegurgitatorException;
}
