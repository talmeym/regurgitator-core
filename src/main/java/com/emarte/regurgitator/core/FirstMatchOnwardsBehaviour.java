package com.emarte.regurgitator.core;

import java.util.*;

import static com.emarte.regurgitator.core.Log.getLog;

final class FirstMatchOnwardsBehaviour implements RulesBehaviour {
	private static final Log log = getLog(FirstMatchOnwardsBehaviour.class);

	@Override
	public List<Object> evaluate(List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId) {
		if(evaluatedStepIds.size() > 0) {
			List<Object> ids = idAndSubsequent(evaluatedStepIds.get(0), allStepIds);
			log.debug("Returning first rule match and subsequent steps '" + ids + "'");
			return ids;
		}

		if(defaultStepId != null) {
			List<Object> ids = idAndSubsequent(defaultStepId, allStepIds);
			log.debug("Returning default and subsequent steps '" + ids + "'");
			return ids;
		}

		throw new IllegalStateException("No rules evaluated true and no default specified in decision");
	}

	private List<Object> idAndSubsequent(Object id, List<Object> allIds) {
		List<Object> ids = new ArrayList<Object>();
		boolean found = false;

		for(Object stepId: allIds) {
			if(stepId.equals(id)) {
				found = true;
			}

			if(found) {
				ids.add(stepId);
			}
		}

		return ids;
	}
}
