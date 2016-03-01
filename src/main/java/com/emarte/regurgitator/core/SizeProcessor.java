package com.emarte.regurgitator.core;

import java.util.Collection;

final class SizeProcessor extends CollectionProcessor {
	private static final Log log = Log.getLog(SizeProcessor.class);

	private boolean asIndex;

	public SizeProcessor(boolean asIndex) {
		this.asIndex = asIndex;
	}

	@Override
	public Object processCollection(Collection collection, Message message) {
		log.debug("Returning size of '" + collection + "'" + (asIndex ? " as last index" : ""));
		return asIndex ? collection.size() - 1 : collection.size();
	}
}
