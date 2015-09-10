package com.emarte.regurgitator.core;

import java.util.*;

public class AllMatchesBehaviour implements RulesBehaviour {
	private Log log = Log.getLog(AllMatchesBehaviour.class);

	@Override
	public List<Object> evaluate(List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId) {
		if(evaluatedStepIds.size() > 0) {
			log.debug("Returning all rule matches '" + evaluatedStepIds + "'");
			return evaluatedStepIds;
		}

		if(defaultStepId != null) {
			log.debug("Returning default '" + defaultStepId + "'");
			return Arrays.asList(defaultStepId);
		}

		throw new IllegalStateException("No rules evaluated true and no default specified in decision");
	}
}
