package com.emarte.regurgitator.core.type;

import com.emarte.regurgitator.core.ParameterType;

import java.util.Collection;

public abstract class AbstractSingleType<TYPE> implements ParameterType<TYPE> {
	@Override
	public Collection toCollectionOf(Object value, Collection collection, ParameterType type) {
		collection.add(type.convert(value));
		return collection;
	}

	@Override
	public TYPE fromCollection(Collection collection, ParameterType type) {
		TYPE total = createNew();

		for (Object obj : collection) {
			total = concat(total, convert(obj));
		}

		return total;
	}
}
