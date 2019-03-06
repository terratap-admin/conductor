package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.Variable;

public class StandardEntry<I, E, O>
        extends PipelineEntry<I, E, O>
{
    private final Filter<I, E, O> processor;
    private final Field<I>        inputField;
    private final Field<E>        extraField;
    private final Variable<O>     outputVar;

    public StandardEntry(final Filter<I, E, O> proc,
                         final Field<I> input,
                         final Field<E> extra,
                         final Variable<O> output)
    {
        processor = proc;
        inputField = input;
        extraField = extra;
        outputVar = output;
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void ignore)
            throws
            FilterException
    {
        execute(transmogrifier,
                processor,
                inputField,
                extraField,
                outputVar);

        return null;
    }
}


