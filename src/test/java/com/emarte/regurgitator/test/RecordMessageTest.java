/*
 * Copyright (C) 2017 Miles Talmey.
 * Distributed under the MIT License (license terms are at http://opensource.org/licenses/MIT).
 */
package com.emarte.regurgitator.test;

import com.emarte.regurgitator.core.*;

public class RecordMessageTest {
//    @Test
    public void testThis() throws RegurgitatorException {
        Message message = new Message(null);

        message.getContext("context1").setValue("something1", "something1");
        message.getContext("context2").setValue("something2", "something2");
        message.getContext("context3").setValue("something3", "something3");

        //new RecordMessage("", "/Users/talmeym/IdeaProjects/regurgitator_git").execute(message);
        new RecordMessage("record-message-1", null).execute(message);
    }
}