package io.transmogrifier.conductor;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.entries.PipelineEntry;

public class PipelineProcessor
        implements Filter<Pipeline, Transmogrifier, Void>
{
    @Override
    public Void perform(final Pipeline pipeline,
                        final Transmogrifier transmogrifier)
            throws
            FilterException
    {
        for(final PipelineEntry<?, ?, ?> entry : pipeline)
        {
            transmogrifier.transform(transmogrifier,
                                     entry);
        }

        return null;
    }
}
