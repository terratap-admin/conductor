package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.ConditionExpanderProcessor;
import io.transmogrifier.conductor.ConditionProcessor;
import io.transmogrifier.conductor.State;

public abstract class ConditionalEntry<I, E, O>
        extends PipelineEntry<I, E, O>
{
    private static final ConditionProcessor         CONDITION_PROCESSOR;
    private static final ConditionExpanderProcessor CONDITION_EXPANDER_PROCESSOR;

    static
    {
        CONDITION_PROCESSOR = new ConditionProcessor();
        CONDITION_EXPANDER_PROCESSOR = new ConditionExpanderProcessor();
    }

    private final String condition;
    private final State  state;

    protected ConditionalEntry(final String cond,
                               final State s)
    {
        condition = cond;
        state = s;
    }

    protected boolean getResult(final Transmogrifier transmogrifier)
            throws
            FilterException
    {
        final boolean result;
        final String  expandedCondition;

        expandedCondition = transmogrifier.transform(condition,
                                                     state,
                                                     CONDITION_EXPANDER_PROCESSOR);

        result = transmogrifier.transform(expandedCondition,
                                          CONDITION_PROCESSOR);

        return result;
    }
}
