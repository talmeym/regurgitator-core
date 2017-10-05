package com.emarte.regurgitator.core;

import java.text.*;

import static com.emarte.regurgitator.core.Log.getLog;
import static com.emarte.regurgitator.core.StringType.stringify;

final class ExtractProcessor implements ValueProcessor {
	private static final Log log = getLog(ExtractProcessor.class);

	private final String format;
	private final int index;

	ExtractProcessor(String format, int index) {
		this.format = format;
		this.index = index;
	}

	@Override
	public Object process(Object value, Message message) throws RegurgitatorException {
		String string = stringify(value);

		try {
			log.debug("Extracting using '{}', index='{}'", format, index);
			MessageFormat formatter = new MessageFormat(format);
			Object[] objects = formatter.parse(string);

			if (objects.length > index) {
				return objects[index];
			}

			throw new RegurgitatorException("Invalid index for message format '" + format + "'");
		} catch (ParseException e) {
			throw new RegurgitatorException("Error parsing value '" + string + "'", e);
		}
	}
}