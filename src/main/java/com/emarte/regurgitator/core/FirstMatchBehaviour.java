package com.emarte.regurgitator.core;

import java.util.*;

public class FirstMatchBehaviour implements RulesBehaviour {
	private Log log = Log.getLog(FirstMatchBehaviour.class);

	@Override
	public List<Object> evaluate(Object decisionId, List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId) {
		if(evaluatedStepIds.size() > 0) {
			Object firstMatch = evaluatedStepIds.get(0);
			log.debug("Returning first rule match '" + firstMatch + "'");
			return Arrays.asList(firstMatch);
		}

		if(defaultStepId != null) {
			log.debug("Returning default '" + defaultStepId + "'");
			return Arrays.asList(defaultStepId);
		}

		throw new IllegalStateException("No rules evaluated true and no default specified in decision '" + decisionId + "'");
	}
}
