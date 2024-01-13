/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package uk.emarte.regurgitator.test;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;

public abstract class TypeTest {
    protected <CONTENTS> Set<CONTENTS> set(CONTENTS... things) {
        return new LinkedHashSet<>(asList(things));
    }

    protected <CONTENTS> List<CONTENTS> list(CONTENTS... things) {
        return asList(things);
    }

    protected Double objOf(double doub) {
        return doub;
    }

    protected Long objOf(long lon) {
        return lon;
    }
}
