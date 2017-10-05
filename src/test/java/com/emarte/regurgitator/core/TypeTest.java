package com.emarte.regurgitator.core;

import java.util.*;

public abstract class TypeTest {
    protected <CONTENTS> Set<CONTENTS> set(CONTENTS... things) {
        return new LinkedHashSet<CONTENTS>(Arrays.asList(things));
    }

    protected <CONTENTS> List<CONTENTS> list(CONTENTS... things) {
        return Arrays.asList(things);
    }
}
