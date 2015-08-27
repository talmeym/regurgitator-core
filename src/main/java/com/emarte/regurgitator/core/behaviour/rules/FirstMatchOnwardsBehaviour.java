package com.emarte.regurgitator.core.behaviour.rules;

import com.emarte.regurgitator.core.*;

import java.util.*;

public class FirstMatchOnwardsBehaviour implements RulesBehaviour {
	private Log log = Log.getLog(FirstMatchOnwardsBehaviour.class);

	@Override
	public List<Object> evaluate(Object decisionId, List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId) {
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

		throw new IllegalStateException("No rules evaluated true and no default specified in decision '" + decisionId + "'");
	}

	private List<Object> idAndSubsequent(Object id, List<Object> allIds) {
		List<Object> ids = Arrays.asList(id);
		boolean found = false;

		for(Object stepId: allIds) {
			if(stepId.equals(id)) {
				found = true;
			} else if(found) {
				ids.add(stepId);
			}
		}

		return ids;
	}
}
