package com.emarte.regurgitator.core;

import java.text.*;

import static com.emarte.regurgitator.core.ParameterType.DefaultImpl.stringify;

class ExtractValue extends Identifiable implements ValueProcessor {
	private static final Log log = Log.getLog(ExtractValue.class);

	private final String format;
	private final int index;

	public ExtractValue(Object id, String format, int index) {
		super(id);
		this.format = format;
		this.index = index;
	}

	@Override
	public Object process(Object value) throws RegurgitatorException {
		String string = stringify(value);

		try {
			log.debug("Extracting using '" + format + "', index='" + index + "'");
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