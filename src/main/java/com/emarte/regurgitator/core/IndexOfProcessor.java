package com.emarte.regurgitator.core;

import java.util.Collection;

final class IndexOfProcessor extends CollectionProcessor {
	private static final Log log = Log.getLog(IndexOfProcessor.class);

	private final ValueSource valueSource;
	private final boolean last;

	public IndexOfProcessor(ValueSource valueSource, boolean last) {
		this.valueSource = valueSource;
		this.last = last;
	}

	@Override
	public Object processCollection(Collection collection, Message message) throws RegurgitatorException {
		Object valueToFind = valueSource.getValue(message, log);

		log.debug("Finding " + (last ? "last " : "") + "index of '" + valueToFind + "' in value '" + collection + "'");
		long index = 0l, lastIndex = -1l;

		for(Object object: collection) {
			if(object.equals(valueToFind)) {
				if(!last) {
					return index;
				}

				lastIndex = index;
			}

			index++;
		}
		return lastIndex;
	}

}
