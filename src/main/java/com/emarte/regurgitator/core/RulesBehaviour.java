/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.List;

public interface RulesBehaviour {
    List<Object> evaluate(List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId);
}
