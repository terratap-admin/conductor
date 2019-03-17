package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.State;
import io.transmogrifier.conductor.Variable;

/**
 * @param <I>
 * @param <E>
 * @param <O>
 */
public class FilterEntry<I, E, O>
        extends Entry<I, E, O>
{
    /**
     *
     */
    private final Filter<I, E, O> filter;

    /**
     *
     */
    private final Field<I> inputField;

    /**
     *
     */
    private final Field<E> extraField;

    /**
     *
     */
    private final Variable<O> outputVar;

    /**
     * @param stat
     * @param f
     * @param input
     */
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

    /**
     * @param stat
     * @param f
     * @param input
     * @param extra
     */
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

    /**
     * @param stat
     * @param f
     * @param input
     * @param output
     */
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

    /**
     * @param stat
     * @param f
     * @param input
     * @param extra
     * @param output
     */
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

    /**
     * @param value
     * @param var
     */
    private void setOutputValue(final O value,
                                final Variable<O> var)
    {
        if(var != null)
        {
            var.setValue(value);
        }
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

    /**
     * @return
     */
    @Override
    public String toString()
    {
        return "FilterEntry: " + filter;
    }
}


