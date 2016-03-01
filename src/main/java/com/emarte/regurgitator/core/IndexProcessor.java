package com.emarte.regurgitator.core;

import java.util.Collection;

import static com.emarte.regurgitator.core.StringType.stringify;
import static java.lang.Long.parseLong;

final class IndexProcessor extends CollectionProcessor {
	private static final Log log = Log.getLog(IndexProcessor.class);

	private final ValueSource valueSource;

	public IndexProcessor(ValueSource valueSource) {
		this.valueSource = valueSource;
	}

	@Override
	public Object processCollection(Collection collection, Message message) throws RegurgitatorException {
		Object valueToUse = valueSource.getValue(message, log);

		long index = parseLong(stringify(valueToUse)), i = 0l;
		log.debug("Finding index '" + index + "' of value '" + collection + "'");

		if(index < 0 || index >= collection.size()) {
			throw new RegurgitatorException("Invalid index: " + index);
		}

		for(Object object: collection) {
			if(i++ == index) {
				return object;
			}
		}

		throw new RegurgitatorException("should never reach here");
	}
}
