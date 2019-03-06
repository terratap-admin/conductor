package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.Variable;

import java.util.List;

public class ForEachEntry<I, E, O>
        extends PipelineEntry<I, E, O>
{
    private final Filter<I, E, O> processor;
    private final List<Field<I>>  inputVars;
    private final Field<E>        extraVar;
    private final Variable<O>     outputVar;

    public ForEachEntry(final Filter<I, E, O> proc,
                        final List<Field<I>> input,
                        final Field<E> extra,
                        final Variable<O> output)
    {
        processor = proc;
        inputVars = input;
        extraVar = extra;
        outputVar = output;
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void inore)
            throws
            FilterException
    {
        final E extra;

        extra = getValue(extraVar);

        for(final Field<I> var : inputVars)
        {
            final I input;

            input = getValue(var);
            transmogrifier.transform(input,
                                     extra,
                                     processor);
        }

        return null;
    }
}