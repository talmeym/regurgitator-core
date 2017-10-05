package com.emarte.regurgitator.core;

import java.util.List;

public interface RulesBehaviour {
    List<Object> evaluate(List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId) throws RegurgitatorException;
}
