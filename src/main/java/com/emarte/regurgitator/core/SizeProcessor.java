package com.emarte.regurgitator.core;

import java.util.Collection;

final class SizeProcessor implements ValueProcessor {
	@Override
	public Object process(Object value, Message message) throws RegurgitatorException {
		if(!(value instanceof Collection)) {
			throw new RegurgitatorException("Parameter is not a collection");
		}

		return ((Collection) value).size();
	}
}
