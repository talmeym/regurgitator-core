package com.emarte.regurgitator.core;

import static com.emarte.regurgitator.core.StringType.stringify;

final class SubstituteProcessor implements ValueProcessor {
	private static final Log log = Log.getLog(SubstituteProcessor.class);

	private final String token;
	private final String replacement;

	SubstituteProcessor(String token, String replacement) {
		this.token = token;
		this.replacement = replacement;
	}

	public Object process(Object value) {
		log.debug("Substituting '" + token + "' for '" + replacement + "'");
		return stringify(value).replaceAll(token, replacement);
	}
}
