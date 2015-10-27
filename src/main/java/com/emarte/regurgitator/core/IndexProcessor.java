package com.emarte.regurgitator.core;

import java.util.Collection;

import static com.emarte.regurgitator.core.StringType.stringify;

public class IndexProcessor implements ValueProcessor {
	private Log log = Log.getLog(this);

	private final ContextLocation source;
	private final String staticValue;

	public IndexProcessor(ContextLocation source, String staticValue) {
		this.source = source;
		this.staticValue = staticValue;
	}

	@Override
	public Object process(Object value, Message message) throws RegurgitatorException {
		if(!(value instanceof Collection)) {
			throw new RegurgitatorException("Parameter is not a collection");
		}

		Object valueToUse;

		if (source != null) {
			Parameter parameter = message.getContextValue(source);

			if (parameter != null) {
				log.debug("Retrieved value from context location '" + source + "'");
				valueToUse = parameter.getValue();
			} else if (staticValue != null) {
				log.debug("defaulting to static value '" + staticValue + "'");
				valueToUse = staticValue;
			} else {
				throw new RegurgitatorException("No value found at context location '" + source + "'");
			}
		} else {
			log.debug("Using static value '" + staticValue + "'");
			valueToUse = staticValue;
		}

		long index = Long.parseLong(stringify(valueToUse)), i = 0l;
		Collection collection = (Collection) value;

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
