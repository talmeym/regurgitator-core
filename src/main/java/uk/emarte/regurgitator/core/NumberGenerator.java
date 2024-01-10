/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.core;

import java.util.Random;

public final class NumberGenerator implements ValueGenerator {
    private static final Random RANDOM = new Random();

    private final Integer max;

    public NumberGenerator(Integer max) {
        this.max = max;
    }

    public NumberGenerator() {
        max = null;
    }

    @Override
    public Object generate() {
        return max != null ? (long) RANDOM.nextInt(max) : RANDOM.nextLong();
    }
}
