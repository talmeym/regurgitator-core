package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.ParameterType.DefaultImpl.stringify;

final class SubstituteValue implements ValueProcessor {
	private static final Log log = Log.getLog(SubstituteValue.class);

	private final String token;
	private final String replacement;

	public SubstituteValue(String token, String replacement) {
		this.token = token;
		this.replacement = replacement;
	}

	public Object process(Object value) {
		log.debug("Substituting '" + token + "' for '" + replacement + "'");
		return stringify(value).replaceAll(token, replacement);
	}
}
