package com.emarte.regurgitator.core;

public class Isolate extends Identifiable implements Step {
	private final Step step;
	private boolean includeSession;

	public Isolate(Object id, Step step, boolean includeSession) {
		super(id);
		this.step = step;
		this.includeSession = includeSession;
	}

	@Override
	public void execute(Message message) throws RegurgitatorException {
		step.execute(new Message(message, includeSession));
	}
}
