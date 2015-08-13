package com.emarte.regurgitator.core;

import java.util.*;

public interface RulesBehaviour extends HasId {
	public List<Object> evaluate(Object decisionId, List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId);
}
