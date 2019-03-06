package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.Variable;

public abstract class PipelineEntry<I, E, O>
        implements Filter<Transmogrifier, Void, Void>
{
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

    protected void setOutputValue(final O value,
                                  final Variable<O> var)
    {
        if(var != null)
        {
            var.setValue(value);
        }
    }

    protected O execute(final Transmogrifier transmogrifier,
                        final Filter<I, E, O> filter,
                        final Field<I> inputField,
                        final Field<E> extraField,
                        final Variable<O> outputVar)
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

        return output;
    }
}
