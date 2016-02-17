package com.emarte.regurgitator.core;

import java.util.Collection;

final class SizeProcessor implements ValueProcessor {
	private static final Log log = Log.getLog(SizeProcessor.class);

	private boolean asIndex;

	public SizeProcessor(boolean asIndex) {
		this.asIndex = asIndex;
	}

	@Override
	public Object process(Object value, Message message) throws RegurgitatorException {
		if(!(value instanceof Collection)) {
			throw new RegurgitatorException("Parameter is not a collection");
		}

		Collection collection = (Collection) value;
		log.debug("Returning size of '" + collection + "'" + (asIndex ? " as last index" : ""));
		return asIndex ? collection.size() - 1 : collection.size();
	}
}
