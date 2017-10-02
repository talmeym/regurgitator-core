package com.emarte.regurgitator.core;

import java.util.Collection;

public abstract class AbstractSingleType<TYPE> implements ParameterType<TYPE> {
	@Override
	public <OTHER, COLLECTION extends Collection<OTHER>> COLLECTION toCollectionOf(TYPE value, COLLECTION collection, ParameterType<OTHER> type) {
		collection.add(type.convert(value));
		return collection;
	}

	@Override
	public TYPE fromCollection(Collection collection) {
		TYPE total = createNew();

		for (Object obj : collection) {
			total = concat(total, convert(obj));
		}

		return total;
	}
}
