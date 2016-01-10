package com.emarte.regurgitator.core;

import java.util.List;

public class Isolate extends Container<Step> implements Step {
	private static final Log log = Log.getLog(Isolate.class);

	private boolean includeSession;
	private boolean includeParameters;

	public Isolate(Object id, List<Step> steps, boolean includeSession, boolean includeParameters) {
		super(id, steps);
		this.includeSession = includeSession;
		this.includeParameters = includeParameters;
	}

	@Override
	public void execute(Message message) throws RegurgitatorException {
		message = new Message(message, includeSession, includeParameters);

		for(Step step : getAll()) {
			log.debug("Executing step '" + step.getId() + "'");
			step.execute(message);
		}
	}
}
