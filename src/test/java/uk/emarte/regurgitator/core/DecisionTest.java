package uk.emarte.regurgitator.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class DecisionTest {
    @Test
    public void testTheTest() throws RegurgitatorException {
        assertTrue(testRule("1", true).evaluate(new Message(null)));
        assertFalse(testRule("1", false).evaluate(new Message(null)));
    }

    @Test
    public void testMatchesExecuted() throws RegurgitatorException {
        List<Step> steps = new ArrayList<>();
        steps.add(new RecordingStep("1"));
        steps.add(new RecordingStep("2"));
        steps.add(new RecordingStep("3"));

        List<Rule> rules = new ArrayList<>();
        rules.add(testRule("1", false));
        rules.add(testRule("2", true));
        rules.add(testRule("3", false));

        new Decision("id", steps, rules, new FirstMatchBehaviour(), null).execute(new Message(null));

        List<Step> executedSteps = steps.stream().filter(s -> ((RecordingStep) s).wasExecuted()).collect(Collectors.toList());
        assertEquals(1, executedSteps.size());
        assertEquals("2", executedSteps.get(0).getId());
    }

    @Test
    public void testDefaultExecuted() throws RegurgitatorException {
        List<Step> steps = new ArrayList<>();
        steps.add(new RecordingStep("1"));
        steps.add(new RecordingStep("2"));
        steps.add(new RecordingStep("3"));

        List<Rule> rules = new ArrayList<>();
        rules.add(testRule("1", false));
        rules.add(testRule("2", false));
        rules.add(testRule("3", false));

        new Decision("id", steps, rules, new FirstMatchBehaviour(), "3").execute(new Message(null));

        List<Step> executedSteps = steps.stream().filter(s -> ((RecordingStep) s).wasExecuted()).collect(Collectors.toList());
        assertEquals(1, executedSteps.size());
        assertEquals("3", executedSteps.get(0).getId());
    }

    @Test(expected = RegurgitatorException.class)
    public void testExceptionWhenNoMatchesNoDefault() throws RegurgitatorException {
        // empty list rules, none can pass, no matches
        // empty list steps, none needed
        // no default step id (null)
        new Decision("id", emptyList(), emptyList(), new FirstMatchBehaviour(), null).execute(new Message(null));
    }

    static class RecordingStep implements Step {
        private final Object id;
        private boolean executed;

        public RecordingStep(Object id) {
            this.id = id;
        }

        @Override
        public Object getId() {
            return id;
        }

        @Override
        public void execute(Message message) throws RegurgitatorException {
            executed = true;
        }

        public boolean wasExecuted() {
            return executed;

        }
    }

    public Rule testRule(Object stepId, boolean evaluateResult) {
        return new Rule("rule", singletonList(new Condition("condition", new ContextLocation("location"), null /* ignored */, false /* ignore */, (parameter, message, conditionValue, expectation) -> evaluateResult)), stepId);
    }
}