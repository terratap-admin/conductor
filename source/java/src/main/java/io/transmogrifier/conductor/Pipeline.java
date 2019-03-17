package io.transmogrifier.conductor;

import io.transmogrifier.conductor.entries.Entry;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Pipeline
        implements Iterable<Entry<?, ?, ?>>
{
    private final Scope                state;
    private final List<Entry<?, ?, ?>> entries;

    public Pipeline(final Scope s,
                    final List<Entry<?, ?, ?>> e)
    {
        state = s;
        entries = new ArrayList<>(e);
    }

    @Override
    public Iterator<Entry<?, ?, ?>> iterator()
    {
        return entries.iterator();
    }

    public Scope getState()
    {
        return state;
    }

    public String toString()
    {
        final StringBuilder builder;

        builder = new StringBuilder();

        for(final Entry<?, ?, ?> entry : entries)
        {
            builder.append(entry);
        }

        return builder.toString();
    }
}
