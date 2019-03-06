package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.State;

public abstract class LoopingEntry<I, E>
        extends ConditionalEntry<I, E, Void>
{
    protected final Filter<I, E, Void> processor;
    protected final Field<I>           inputVar;
    protected final Field<E>           extraVar;

    protected LoopingEntry(final String cond,
                           final Filter<I, E, Void> proc,
                           final Field<I> input,
                           final Field<E> extra,
                           final State s)
    {
        super(cond,
              s);

        processor = proc;
        inputVar = input;
        extraVar = extra;
    }

    protected void execute(final Transmogrifier transmogrifier)
            throws
            FilterException
    {
        final I input;
        final E extra;

        input = getValue(inputVar);
        extra = getValue(extraVar);
        transmogrifier.transform(input,
                                 extra,
                                 processor);
    }
}
