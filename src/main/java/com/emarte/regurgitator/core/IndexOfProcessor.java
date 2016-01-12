package com.emarte.regurgitator.core;

import java.util.Collection;

final class IndexOfProcessor implements ValueProcessor {
	private static final Log log = Log.getLog(IndexOfProcessor.class);

	private final ValueSource valueSource;
	private final boolean last;

	public IndexOfProcessor(ValueSource valueSource, boolean last) {
		this.valueSource = valueSource;
		this.last = last;
	}

	@Override
	public Object process(Object value, Message message) throws RegurgitatorException {
		if(!(value instanceof Collection)) {
			throw new RegurgitatorException("Parameter is not a collection");
		}

		Object valueToFind = valueSource.getValue(message, log);

		log.debug("Finding " + (last ? "last " : "") + "index of '" + valueToFind + "' in value '" + value + "'");
		long index = 0l, lastIndex = -1l;

		for(Object object: (Collection) value) {
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
