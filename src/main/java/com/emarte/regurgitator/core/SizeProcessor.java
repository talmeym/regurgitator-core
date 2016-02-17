package com.emarte.regurgitator.core;

import java.util.Collection;

final class SizeProcessor implements ValueProcessor {
	private static final Log log = Log.getLog(SizeProcessor.class);

	private boolean lastIndex;

	public SizeProcessor(boolean lastIndex) {
		this.lastIndex = lastIndex;
	}

	@Override
	public Object process(Object value, Message message) throws RegurgitatorException {
		if(!(value instanceof Collection)) {
			throw new RegurgitatorException("Parameter is not a collection");
		}

		Collection collection = (Collection) value;
		log.debug("Returning size of '" + collection + "'" + (lastIndex ? " as last index" : ""));
		return lastIndex ? collection.size() - 1 : collection.size();
	}
}
