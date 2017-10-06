/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.core;

import java.util.UUID;

final class UuidGenerator implements ValueGenerator {
    @Override
    public Object generate() throws RegurgitatorException {
        return UUID.randomUUID();
    }
}
