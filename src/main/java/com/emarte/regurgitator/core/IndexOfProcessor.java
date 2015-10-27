package com.emarte.regurgitator.core;

import java.util.Collection;

public class IndexOfProcessor implements ValueProcessor {
	private Log log = Log.getLog(this);

	private final ContextLocation source;
	private final String staticValue;
	private final boolean last;

	public IndexOfProcessor(ContextLocation source, String staticValue, boolean last) {
		this.source = source;
		this.staticValue = staticValue;
		this.last = last;
	}

	@Override
	public Object process(Object value, Message message) throws RegurgitatorException {
		if(!(value instanceof Collection)) {
			throw new RegurgitatorException("Parameter is not a collection");
		}

		Object valueToFind;

		if (source != null) {
			Parameter parameter = message.getContextValue(source);

			if (parameter != null) {
				log.debug("Retrieved value from context location '" + source + "'");
				valueToFind = parameter.getValue();
			} else if (staticValue != null) {
				log.debug("defaulting to static value '" + staticValue + "'");
				valueToFind = staticValue;
			} else {
				throw new RegurgitatorException("No value found at context location '" + source + "'");
			}
		} else {
			log.debug("Using static value '" + staticValue + "'");
			valueToFind = staticValue;
		}

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
