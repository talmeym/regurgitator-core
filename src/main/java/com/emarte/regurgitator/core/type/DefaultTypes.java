package com.emarte.regurgitator.core.type;

import com.emarte.regurgitator.core.ParameterType;

public class DefaultTypes {
	public static final ParameterType STRING = new StringType();
	public static final ParameterType NUMBER = new NumberType();
	public static final ParameterType DECIMAL = new DecimalType();
	public static final ParameterType LIST_STRING = new ListOfStringType();
	public static final ParameterType LIST_NUMBER = new ListOfNumberType();
	public static final ParameterType LIST_DECIMAL = new ListOfDecimalType();
	public static final ParameterType SET_STRING = new SetOfStringType();
	public static final ParameterType SET_NUMBER = new SetOfNumberType();
	public static final ParameterType SET_DECIMAL = new SetOfDecimalType();
}
