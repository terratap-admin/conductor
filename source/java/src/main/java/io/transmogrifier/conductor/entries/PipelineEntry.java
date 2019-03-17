package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

/**
 *
 */
public class PipelineEntry
        extends Entry<Pipeline, State, Void>
{
    /**
     *
     */
    private final Pipeline pipeline;

    /**
     * @param stat
     * @param pipe
     */
    public PipelineEntry(final State stat,
                         final Pipeline pipe)
    {
        super(stat);

        pipeline = pipe;
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
        for(final Entry<?, ?, ?> entry : pipeline)
        {
            transmogrifier.transform(transmogrifier,
                                     entry);
        }

        return null;
    }

    /**
     * @return
     */
    @Override
    public String toString()
    {
        return "Pipeline";
    }
}


