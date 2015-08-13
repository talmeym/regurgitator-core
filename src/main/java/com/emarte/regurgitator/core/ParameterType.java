package com.emarte.regurgitator.core;

import java.util.*;

public interface ParameterType<TYPE> extends HasId {
	public TYPE createNew();

	public TYPE concat(TYPE prefix, TYPE suffix);

	public TYPE remove(TYPE existingValue, TYPE newValue);

	public boolean validate(Object value);

	public TYPE convert(Object value);

	public boolean contains(TYPE container, TYPE value);

	public Collection toCollectionOf(TYPE value, Collection collection, ParameterType type);

	public TYPE fromCollection(Collection collection, ParameterType type);

	public static class TypeFinder {
		public static ServiceLoader<ParameterType> LOADER = ServiceLoader.load(ParameterType.class);
		private static Log log = Log.getLog(TypeFinder.class);

		public static ParameterType findType(String name) throws RegurgitatorException {
			for(Iterator<ParameterType> iterator = LOADER.iterator(); iterator.hasNext(); ) {
				ParameterType type = iterator.next();

				if(name.equals(type.getId())) {
					log.debug("Found parameter type: " + name);
					return type;
				}
			}

			throw new RegurgitatorException("Parameter Type not found: " + name);
		}
	}
}
