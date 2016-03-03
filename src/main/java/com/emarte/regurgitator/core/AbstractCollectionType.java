package com.emarte.regurgitator.core;

import java.util.Collection;

public abstract class AbstractCollectionType<TYPE extends Collection> implements ParameterType<TYPE> {
	@Override
	public TYPE concat(TYPE prefix, TYPE suffix) {
		TYPE result = createNew();
		result.addAll(prefix);
		result.addAll(suffix);
		return result;
	}

	@Override
	public TYPE remove(TYPE existingValue, TYPE newValue) {
		TYPE result = createNew();
		result.addAll(existingValue);
		result.removeAll(newValue);
		return result;
	}

	public boolean validateCollection(Collection value, ParameterType type) {
		for(Object obj: value) {
			if(!type.validate(obj)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public Collection toCollectionOf(TYPE value, Collection collection, ParameterType type) {
		for(Object obj : value) {
			collection.add(type.convert(obj));
		}

		return collection;
	}

	@Override
	public TYPE fromCollection(Collection collection, ParameterType type) {
		TYPE value = createNew();

		for(Object obj : collection) {
			value.add(type.convert(obj));
		}

		return value;
	}

	@Override
	public boolean contains(TYPE container, TYPE contained) {
		return container.containsAll(contained);
	}
}
