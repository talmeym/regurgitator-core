package com.emarte.regurgitator.core;

import java.util.*;

public interface ParameterType {
	public String getName();

	public Object concat(Object prefix, Object suffix);

	public Object remove(Object existingValue, Object newValue);

	public boolean validate(Object value);

	public Object convert(Object value);

	public boolean contains(Object container, Object value);

	public static enum DefaultImpl implements ParameterType {
		STRING {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return ((String) prefix).concat((String) suffix);
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return ((String)existingValue).replace(((String)newValue), "");
			}

			@Override
			public boolean validate(Object value) {
				return value instanceof String;
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (NUMBER.validate(value) || DECIMAL.validate(value)) {
					return value.toString();
				}

				if (value instanceof Collection) {
					return convertCollectionToString((Collection) value);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((String)container).contains((String) contained);
			}
		},
		NUMBER {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return (Long) prefix + (Long) suffix;
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return (Long) existingValue - (Long) newValue;
			}

			@Override
			public boolean validate(Object value) {
				return value instanceof Long;
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (STRING.validate(value)) {
					return Long.parseLong((String) value);
				}

				if (value instanceof Collection) {
					return addUpCollection((Collection) value, NUMBER);
				}

				if(value instanceof Integer) {
					return (long) (Integer) value;
				}

				if(DECIMAL.validate(value)) {
					return Math.round((Double)value);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((Long)container) >= (Long) contained;
			}
		},
		DECIMAL {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return (Double) prefix + (Double) suffix;
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return (Double) existingValue - (Double) newValue;
			}

			@Override
			public boolean validate(Object value) {
				return value instanceof Double;
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (STRING.validate(value) || NUMBER.validate(value)) {
					return Double.parseDouble(value.toString());
				}

				if (value instanceof Collection) {
					return addUpCollection((Collection) value, DECIMAL);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((Double)container) >= (Double) contained;
			}
		},
		LIST_STRING {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return concatCollections((List) prefix, (List) suffix, new ArrayList());
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return removeFromCollection((List) existingValue, (List) newValue, new ArrayList());

			}

			@Override
			public boolean validate(Object value) {
				return value instanceof List && validateCollection((Collection) value, STRING);
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (STRING.validate(value)) {
					return Arrays.asList(((String) value).split(","));
				}

				if (NUMBER.validate(value) || DECIMAL.validate(value)) {
					return Arrays.asList(value.toString());
				}

				if (value instanceof Collection) {
					return convertCollection((Collection) value, new ArrayList(), STRING);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((List)container).containsAll((List) contained);
			}

		},
		SET_STRING {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return concatCollections((Set) prefix, (Set) suffix, new LinkedHashSet());
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return removeFromCollection((Set) existingValue, (Set) newValue, new LinkedHashSet());
			}

			@Override
			public boolean validate(Object value) {
				return value instanceof Set && validateCollection((Collection) value, STRING);
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (STRING.validate(value)) {
					return new LinkedHashSet<String>(Arrays.asList(((String) value).split(",")));
				}

				if (NUMBER.validate(value) || DECIMAL.validate(value)) {
					return new LinkedHashSet(Arrays.asList(STRING.convert(value)));
				}

				if (value instanceof Collection) {
					return convertCollection((Collection) value, new LinkedHashSet(), STRING);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((Set)container).containsAll((Set) contained);
			}
		},
		LIST_NUMBER {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return concatCollections((List) prefix, (List) suffix, new ArrayList());
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return removeFromCollection((List) existingValue, (List) newValue, new ArrayList());
			}

			@Override
			public boolean validate(Object value) {
				return value instanceof List && validateCollection((List) value, NUMBER);
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (STRING.validate(value)) {
					return convertStringToCollection((String) value, new ArrayList(), NUMBER);
				}

				if (NUMBER.validate(value)) {
					return Arrays.asList(value);
				}

				if (value instanceof Collection) {
					return convertCollection((Collection) value, new ArrayList(), NUMBER);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((List)container).containsAll((List) contained);
			}
		},
		SET_NUMBER {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return concatCollections((Set) prefix, (Set) suffix, new LinkedHashSet());
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return removeFromCollection((Set) existingValue, (Set) newValue, new LinkedHashSet());
			}

			@Override
			public boolean validate(Object value) {
				return value instanceof Set && validateCollection((Collection) value, NUMBER);
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (STRING.validate(value)) {
					return convertStringToCollection((String) value, new LinkedHashSet(), NUMBER);
				}

				if (NUMBER.validate(value)) {
					return new LinkedHashSet(Arrays.asList(value));
				}

				if (value instanceof Collection) {
					return convertCollection((Collection) value, new LinkedHashSet(), NUMBER);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((Set)container).containsAll((Set) contained);
			}
		},
		LIST_DECIMAL {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return concatCollections((List) prefix, (List) suffix, new ArrayList());
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return removeFromCollection((List) existingValue, (List) newValue, new ArrayList());
			}

			@Override
			public boolean validate(Object value) {
				return value instanceof List && validateCollection((Collection) value, DECIMAL);
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (STRING.validate(value)) {
					return convertStringToCollection((String) value, new ArrayList(), DECIMAL);
				}

				if (DECIMAL.validate(value) || NUMBER.validate(value)) {
					return Arrays.asList(DECIMAL.convert(value));
				}

				if (value instanceof Collection) {
					return convertCollection((Collection) value, new ArrayList(), DECIMAL);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((List)container).containsAll((List) contained);
			}
		},
		SET_DECIMAL {
			@Override
			public Object concat(Object prefix, Object suffix) {
				validateOrThrow(prefix, this);
				validateOrThrow(suffix, this);
				return concatCollections((Set) prefix, (Set) suffix, new LinkedHashSet());
			}

			@Override
			public Object remove(Object existingValue, Object newValue) {
				validateOrThrow(existingValue, this);
				validateOrThrow(newValue, this);
				return removeFromCollection((Set) existingValue, (Set) newValue, new LinkedHashSet());
			}

			@Override
			public boolean validate(Object value) {
				return value instanceof Set && validateCollection((Collection) value, DECIMAL);
			}

			@Override
			public Object convert(Object value) {
				if (validate(value)) {
					return value;
				}

				if (STRING.validate(value)) {
					return convertStringToCollection((String) value, new LinkedHashSet(), DECIMAL);
				}

				if (DECIMAL.validate(value) || NUMBER.validate(value)) {
					return new LinkedHashSet(Arrays.asList(DECIMAL.convert(value)));
				}

				if (value instanceof Collection) {
					return convertCollection((Collection) value, new LinkedHashSet(), DECIMAL);
				}

				throw new IllegalArgumentException("Cannot convert " + value.getClass().getName() + " to " + name());
			}

			@Override
			public boolean contains(Object container, Object contained) {
				return ((Set)container).containsAll((Set) contained);
			}
		};

		private static Object addUpCollection(Collection collection, ParameterType type) {
			Object total = type.convert("0");

			for (Object obj : collection) {
				total = type.concat(total, type.convert(obj));
			}

			return total;
		}

		private static boolean validateCollection(Collection collection, ParameterType type) {
			for (Object obj : collection) {
				if (!type.validate(obj)) {
					return false;
				}
			}

			return true;
		}

		private static Object concatCollections(Collection prefix, Collection suffix, Collection result) {
			result.addAll(prefix);
			result.addAll(suffix);
			return result;
		}

		private static Object removeFromCollection(Collection existingValue, Collection newValue, Collection result) {
			result.addAll(existingValue);
			result.removeAll(newValue);
			return result;
		}

		private static Object convertCollectionToString(Collection value) {
			StringBuilder buffer = new StringBuilder();

			for (Iterator iterator = value.iterator(); iterator.hasNext(); ) {
				buffer.append(iterator.next().toString());

				if (iterator.hasNext()) {
					buffer.append(",");
				}
			}

			return buffer.toString();
		}

		private static Object convertStringToCollection(String value, Collection result, ParameterType type) {
			String[] strings = value.split(",");

			for (String string : strings) {
				result.add(type.convert(string));
			}

			return result;
		}

		private static Object convertCollection(Collection value, Collection result, ParameterType type) {
			for (Object obj : value) {
				result.add(type.convert(obj));
			}

			return result;
		}

		private static void validateOrThrow(Object object, ParameterType type) {
			if(!type.validate(object)) {
				throw new IllegalArgumentException("Object '" + object + "' is not of type '" + type.getName() + "'");
			}
		}

		public static String stringify(Parameter parameter) {
			return parameter != null ? (String) STRING.convert(parameter.getValue()) : null;
		}

		public static String stringify(Object value) {
			return value != null ? (String) STRING.convert(value) : null;
		}

		public String getName() {
			return name();
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
