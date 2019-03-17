package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;
import io.transmogrifier.conductor.Variable;

import java.util.List;

/**
 * @param <I>
 * @param <E>
 */
public class ForEachEntry<I, E>
        extends Entry<I, E, Void>
{
    /**
     *
     */
    private final Pipeline pipeline;

    /**
     *
     */
    private final Field<List<I>> inputField;

    /**
     *
     */
    private final Field<E> extraField;

    /**
     *
     */
    private final Variable<I> itemField;

    /**
     * @param stat
     * @param pipe
     * @param item
     * @param input
     */
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

    /**
     * @param stat
     * @param pipe
     * @param item
     * @param input
     * @param extra
     */
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
        final List<I> input;

        input = getValue(inputField);

        for(final I value : input)
        {
            itemField.setValue(value);
            execute(pipeline);
        }

        return null;
    }

    /**
     * @return
     */
    @Override
    public String toString()
    {
        return "ForEachEntry: " + pipeline;
    }
}