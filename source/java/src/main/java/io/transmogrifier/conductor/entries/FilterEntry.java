package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.State;
import io.transmogrifier.conductor.Variable;

public class FilterEntry<I, E, O>
        extends Entry<I, E, O>
{
    private final Filter<I, E, O> filter;
    private final Field<I>        inputField;
    private final Field<E>        extraField;
    private final Variable<O>     outputVar;

    public FilterEntry(final State stat,
                       final Filter<I, E, O> f,
                       final Field<I> input)
    {
        this(stat,
             f,
             input,
             null,
             null);
    }

    public FilterEntry(final State stat,
                       final Filter<I, E, O> f,
                       final Field<I> input,
                       final Field<E> extra)
    {
        this(stat,
             f,
             input,
             extra,
             null);
    }

    public FilterEntry(final State stat,
                       final Filter<I, E, O> f,
                       final Field<I> input,
                       final Variable<O> output)
    {
        this(stat,
             f,
             input,
             null,
             output);
    }

    public FilterEntry(final State stat,
                       final Filter<I, E, O> f,
                       final Field<I> input,
                       final Field<E> extra,
                       final Variable<O> output)
    {
        super(stat);

        filter = f;
        inputField = input;
        extraField = extra;
        outputVar = output;
    }

    private void setOutputValue(final O value,
                                final Variable<O> var)
    {
        if(var != null)
        {
            var.setValue(value);
        }
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void ignore)
            throws
            FilterException
    {
        final I input;
        final E extra;
        final O output;

        input = getValue(inputField);
        extra = getValue(extraField);
        output = transmogrifier.transform(input,
                                          extra,
                                          filter);
        setOutputValue(output,
                       outputVar);

        return null;
    }

    @Override
    public String toString()
    {
        return "FilterEntry: " + filter;
    }
}


