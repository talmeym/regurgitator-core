/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import java.util.*;

import static java.util.Arrays.asList;

public abstract class TypeTest {
    protected <CONTENTS> Set<CONTENTS> set(CONTENTS... things) {
        return new LinkedHashSet<CONTENTS>(asList(things));
    }

    protected <CONTENTS> List<CONTENTS> list(CONTENTS... things) {
        return asList(things);
    }
}
