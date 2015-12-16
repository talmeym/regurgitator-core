package com.emarte.regurgitator.core;

import java.util.Collection;

import static com.emarte.regurgitator.core.StringType.stringify;

public class IndexProcessor implements ValueProcessor {
	private static final Log log = Log.getLog(IndexProcessor.class);

	private final ValueSource valueSource;

	public IndexProcessor(ValueSource valueSource) {
		this.valueSource = valueSource;
	}

	@Override
	public Object process(Object value, Message message) throws RegurgitatorException {
		if(!(value instanceof Collection)) {
			throw new RegurgitatorException("Parameter is not a collection");
		}

		Object valueToUse = valueSource.getValue(message, log);

		long index = Long.parseLong(stringify(valueToUse)), i = 0l;
		Collection collection = (Collection) value;
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
