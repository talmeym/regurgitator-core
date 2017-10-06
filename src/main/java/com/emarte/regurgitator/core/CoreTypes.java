/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

public class CoreTypes {
    public static final StringType STRING = new StringType();
    public static final NumberType NUMBER = new NumberType();
    public static final DecimalType DECIMAL = new DecimalType();
    public static final ListOfStringType LIST_OF_STRING = new ListOfStringType();
    public static final ListOfNumberType LIST_OF_NUMBER = new ListOfNumberType();
    public static final ListOfDecimalType LIST_OF_DECIMAL = new ListOfDecimalType();
    public static final SetOfStringType SET_OF_STRING = new SetOfStringType();
    public static final SetOfNumberType SET_OF_NUMBER = new SetOfNumberType();
    public static final SetOfDecimalType SET_OF_DECIMAL = new SetOfDecimalType();
}
