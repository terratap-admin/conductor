package io.transmogrifier.conductor;

import io.transmogrifier.conductor.entries.PipelineEntry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pipeline
        implements Iterable<PipelineEntry<?, ?, ?>>
{
    private final List<PipelineEntry<?, ?, ?>> entries;

    public Pipeline(final List<PipelineEntry<?, ?, ?>> e)
    {
        entries = new ArrayList<>(e);
    }

    @Override
    public Iterator<PipelineEntry<?, ?, ?>> iterator()
    {
        return entries.iterator();
    }
}
