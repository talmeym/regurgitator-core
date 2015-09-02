package com.emarte.regurgitator.core;

import java.util.List;

public interface RulesBehaviour {
	public List<Object> evaluate(Object decisionId, List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId);
}
