package com.emarte.regurgitator.core;

public abstract class ParameterExtractor extends Identifiable implements Step {
    private final ParameterPrototype prototype;
	private final String context;
	private ValueProcessor processor;

    public ParameterExtractor(Object id, ParameterPrototype prototype, String context, ValueProcessor processor) {
        super(id);
        this.prototype = prototype;
		this.context = context;
		this.processor = processor;
	}

    public ParameterPrototype getPrototype() {
        return prototype;
    }

	public String getContext() {
		return context;
	}

    @Override
    public final void execute(Message message) throws RegurgitatorException {
		ParameterType type = prototype.getType();
		Object value = extractValue(message);

		if(processor != null) {
			value = processor.process(value, message);
		}

		message.getContext(context).setValue(new Parameter(prototype, type.convert(value)));
    }

    public abstract Object extractValue(Message message) throws RegurgitatorException;
}
