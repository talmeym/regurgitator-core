package com.emarte.regurgitator.core;

public interface ConditionBehaviour {
	public boolean evaluate(ContextLocation location, Parameter parameter, Object conditionValue, boolean expectation);

	public static enum DefaultImpl implements ConditionBehaviour {
		CONTAINS {
			@Override
			public boolean evaluate(ContextLocation location, Parameter parameter, Object conditionValue, boolean expectation) {
				boolean contains = false;

				if(parameter != null) {
					ParameterType parameterType = parameter.getType();
					contains = parameterType.contains(parameter.getValue(), parameterType.convert(conditionValue));
				}

				log.debug("Parameter '" + location + (contains ?  "' contain"  : "' does not contain") + " value '" + conditionValue + "'");
				return contains == expectation;
			}
		},
		EQUALS {
			@Override
			public boolean evaluate(ContextLocation location, Parameter parameter, Object conditionValue, boolean expectation) {
				boolean equals = false;

				if(parameter != null) {
					equals = parameter.getValue().equals(parameter.getType().convert(conditionValue));
				}

				log.debug("Parameter '" + location + (equals ? "' equals" : "' does not equal") + " value '" + conditionValue + "'");
				return equals == expectation;
			}
		},
		EXISTS {
			@Override
			public boolean evaluate(ContextLocation location, Parameter parameter, Object conditionValue, boolean notUsed) {
				Boolean expectation = Boolean.valueOf(conditionValue.toString());
				boolean exists = parameter != null;
				log.debug("Parameter '" + location + (exists ? "' exists" : "' does not exist"));
				return exists == expectation;
			}
		};

		private static final Log log = Log.getLog(ConditionBehaviour.class);

		static boolean contains(String name) {
			for(DefaultImpl value: values()) {
				if(value.name().equals(name)) {
					return true;
				}
			}

			return false;
		}
	}
}