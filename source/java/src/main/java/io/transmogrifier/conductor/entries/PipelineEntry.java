package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

public class PipelineEntry
        extends Entry<Pipeline, State, Void>
{
    private final Pipeline pipeline;

    public PipelineEntry(final State stat,
                         final Pipeline pipe)
    {
        super(stat);

        pipeline = pipe;
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void ignore)
            throws
            FilterException
    {
        for(final Entry<?, ?, ?> entry : pipeline)
        {
            transmogrifier.transform(transmogrifier,
                                     entry);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "Pipeline";
    }
}


