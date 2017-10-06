/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.Random;

final class NumberGenerator implements ValueGenerator {
    private static final Random RANDOM = new Random();

    private final Integer max;

    NumberGenerator(Integer max) {
        this.max = max;
    }

    NumberGenerator() {
        max = null;
    }

    @Override
    public Object generate() throws RegurgitatorException {
        return max != null ? (long) RANDOM.nextInt(max) : RANDOM.nextLong();
    }
}
