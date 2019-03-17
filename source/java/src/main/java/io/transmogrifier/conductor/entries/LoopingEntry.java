package io.transmogrifier.conductor.entries;

import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

/**
 * @param <I>
 * @param <E>
 */
public abstract class LoopingEntry<I, E>
        extends ConditionalEntry<I, E, Void>
{
    /**
     *
     */
    protected final Pipeline pipeline;

    /**
     * @param stat
     * @param p
     * @param cond
     */
    protected LoopingEntry(final State stat,
                           final Pipeline p,
                           final String cond)
    {
        super(stat,
              cond);

        pipeline = p;
    }

    /**
     * @return
     */
    @Override
    public String toString()
    {
        return pipeline + " : " + super.toString();
    }
}
