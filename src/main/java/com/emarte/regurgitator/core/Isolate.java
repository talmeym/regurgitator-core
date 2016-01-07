package com.emarte.regurgitator.core;

public class Isolate extends Identifiable implements Step {
	private final Step step;
	private boolean includeSession;
	private boolean includeParameters;

	public Isolate(Object id, Step step, boolean includeSession, boolean includeParameters) {
		super(id);
		this.step = step;
		this.includeSession = includeSession;
		this.includeParameters = includeParameters;
	}

	@Override
	public void execute(Message message) throws RegurgitatorException {
		step.execute(new Message(message, includeSession, includeParameters));
	}
}
