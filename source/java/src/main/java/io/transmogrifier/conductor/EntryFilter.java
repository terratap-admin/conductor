package io.transmogrifier.conductor;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.entries.PipelineEntry;

public class EntryFilter<I, E, O>
        extends PipelineEntry<I, E, O>
{
    private final PipelineEntry<I, E, O> entry;

    public EntryFilter(final PipelineEntry<I, E, O> e)
    {
        entry = e;
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void extra)
            throws
            FilterException
    {
        transmogrifier.transform(transmogrifier,
                                 entry);

        return null;
    }
}
