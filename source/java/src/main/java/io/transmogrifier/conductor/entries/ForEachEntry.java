package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;
import io.transmogrifier.conductor.Variable;

import java.util.List;

public class ForEachEntry<I, E>
        extends Entry<I, E, Void>
{
    private final Pipeline       pipeline;
    private final Field<List<I>> inputField;
    private final Field<E>       extraField;
    private final Variable<I>    itemField;

    public ForEachEntry(final State stat,
                        final Pipeline pipe,
                        final Variable<I> item,
                        final Field<List<I>> input)
    {
        this(stat,
             pipe,
             item,
             input,
             null);
    }

    public ForEachEntry(final State stat,
                        final Pipeline pipe,
                        final Variable<I> item,
                        final Field<List<I>> input,
                        final Field<E> extra)
    {
        super(stat);

        pipeline = pipe;
        inputField = input;
        extraField = extra;
        itemField = item;
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void ignore)
            throws
            FilterException
    {
        final List<I> input;

        input = getValue(inputField);

        for(final I value : input)
        {
            itemField.setValue(value);
            execute(pipeline);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "ForEachEntry: " + pipeline;
    }
}