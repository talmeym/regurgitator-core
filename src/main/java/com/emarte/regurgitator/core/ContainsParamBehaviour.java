package com.emarte.regurgitator.core;

public class ContainsParamBehaviour implements ConditionBehaviour {
	private static Log log = Log.getLog(ContainsParamBehaviour.class);

	@Override
	public boolean evaluate(ContextLocation location, Message message, String conditionValue, boolean expectation) {
		boolean contains = false;
		Parameter parameter = message.getContextValue(location);

		if(parameter != null) {
			Parameter comparisonParameter = message.getContextValue(new ContextLocation(conditionValue));

			if(comparisonParameter != null) {
				ParameterType parameterType = parameter.getType();
				contains = parameterType.contains(parameter.getValue(), parameterType.convert(comparisonParameter.getValue()));
			}

		}

		log.debug("Parameter '" + location + (contains ? "' contains" : "' does not contain") + " parameter '" + conditionValue + "'");
		return contains == expectation;
	}
}
