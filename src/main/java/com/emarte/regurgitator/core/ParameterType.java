package com.emarte.regurgitator.core;

import java.util.Collection;

public interface ParameterType<TYPE> {
	public TYPE createNew();

	public TYPE concat(TYPE prefix, TYPE suffix);

	public TYPE remove(TYPE existingValue, TYPE newValue);

	public boolean validate(Object value);

	public TYPE convert(Object value);

	public boolean contains(TYPE container, TYPE value);

	public Collection toCollectionOf(TYPE value, Collection collection, ParameterType type);

	public TYPE fromCollection(Collection collection, ParameterType type);
}
