package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

/**
 * @param <I>
 * @param <E>
 */
public class DoWhileEntry<I, E>
        extends LoopingEntry<I, E>
{
    /**
     * @param stat
     * @param pipe
     * @param cond
     */
    public DoWhileEntry(final State stat,
                        final Pipeline pipe,
                        final String cond)
    {
        super(stat,
              pipe,
              cond);
    }

    /**
     * @param transmogrifier
     * @param ignore
     * @return
     * @throws FilterException
     */
    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void ignore)
            throws
            FilterException
    {
        do
        {
            execute(pipeline);
        } while(getResult(transmogrifier));

        return null;
    }

    /**
     * @return
     */
    @Override
    public String toString()
    {
        return "DoWhileEntry: " + super.toString();
    }
}
