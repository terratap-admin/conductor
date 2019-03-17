package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Conductor;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

public abstract class Entry<I, E, O>
        implements Filter<Transmogrifier, Void, Void>
{
    protected final State state;

    protected Entry(final State stat)
    {
        state = stat;
    }

    protected <T> T getValue(final Field<T> field)
    {
        final T value;

        if(field == null)
        {
            value = null;
        }
        else
        {
            value = field.getValue();
        }

        return value;
    }

    protected void execute(final Pipeline pipeline)
            throws
            FilterException
    {
        final Transmogrifier transmogrifier;
        final Conductor      conductor;

        transmogrifier = state.getTransmogrifier();
        conductor = state.getConductor();
        transmogrifier.transform(pipeline,
                                 state,
                                 conductor);
    }

}
