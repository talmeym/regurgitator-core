package com.emarte.regurgitator.core;

import java.util.*;

public interface RulesBehaviour {
	public List<Object> evaluate(Object decisionId, List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId);

	public static enum DefaultImpl implements RulesBehaviour {
		FIRST_MATCH {
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
		},
		ALL_MATCHES {
			@Override
			public List<Object> evaluate(Object decisionId, List<Object> evaluatedStepIds, List<Object> allStepIds, Object defaultStepId) {
				if(evaluatedStepIds.size() > 0) {
					log.debug("Returning all rule matches '" + evaluatedStepIds + "'");
					return evaluatedStepIds;
				}

				if(defaultStepId != null) {
					log.debug("Returning default '" + defaultStepId + "'");
					return Arrays.asList(defaultStepId);
				}

				throw new IllegalStateException("No rules evaluated true and no default specified in decision '" + decisionId + "'");
			}
		},
		FIRST_MATCH_ONWARDS {
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
				ids.addAll(findSubsequentIds(id, allIds));
				return ids;
			}
		};

		private static final Log log = Log.getLog(RulesBehaviour.class);

		private static List<Object> findSubsequentIds(Object id, List<Object> stepIds) {
			List<Object> ids = new ArrayList<Object>();
			boolean found = false;

			for(Object stepId: stepIds) {
				if(stepId.equals(id)) {
					found = true;
				} else if(found) {
					ids.add(stepId);
				}
			}

			return ids;
		}

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
