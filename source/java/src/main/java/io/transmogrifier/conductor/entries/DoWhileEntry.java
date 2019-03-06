package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.State;

public class DoWhileEntry<I, E>
        extends LoopingEntry<I, E>
{
    public DoWhileEntry(final Filter<I, E, Void> proc,
                        final Field<I> input,
                        final Field<E> extra,
                        final String cond,
                        final State s)
    {
        super(cond,
              proc,
              input,
              extra,
              s);
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void ignore)
            throws
            FilterException
    {
        do
        {
            execute(transmogrifier);
        } while(getResult(transmogrifier));

        return null;
    }
}
