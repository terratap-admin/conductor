package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.ConditionExpanderFilter;
import io.transmogrifier.conductor.ConditionFilter;
import io.transmogrifier.conductor.Scope;
import io.transmogrifier.conductor.State;

/**
 * @param <I>
 * @param <E>
 * @param <O>
 */
public abstract class ConditionalEntry<I, E, O>
        extends Entry<I, E, O>
{
    /**
     *
     */
    private static final ConditionFilter CONDITION_FILTER;

    /**
     *
     */
    private static final ConditionExpanderFilter CONDITION_EXPANDER_FILTER;

    static
    {
        CONDITION_FILTER = new ConditionFilter();
        CONDITION_EXPANDER_FILTER = new ConditionExpanderFilter();
    }

    /**
     *
     */
    private final String condition;

    /**
     * @param stat
     * @param cond
     */
    protected ConditionalEntry(final State stat,
                               final String cond)
    {
        super(stat);

        condition = cond;
    }

    /**
     * @param transmogrifier
     * @return
     * @throws FilterException
     */
    protected boolean getResult(final Transmogrifier transmogrifier)
            throws
            FilterException
    {
        final boolean result;
        final String  expandedCondition;
        final Scope   scope;

        scope = state.getScope();
        expandedCondition = transmogrifier.transform(condition,
                                                     scope,
                                                     CONDITION_EXPANDER_FILTER);

        result = transmogrifier.transform(expandedCondition,
                                          CONDITION_FILTER);

        return result;
    }

    /**
     * @return
     */
    public String toString()
    {
        return condition;
    }
}
