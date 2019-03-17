package io.transmogrifier.conductor;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.entries.Entry;

/**
 *
 */
public class PipelineFilter
        implements Filter<Pipeline, State, Void>
{
    /**
     * @param pipeline
     * @param state
     * @return
     * @throws FilterException
     */
    @Override
    public Void perform(final Pipeline pipeline,
                        final State state)
            throws
            FilterException
    {
        final Transmogrifier transmogrifier;

        if(pipeline == null)
        {
            throw new IllegalArgumentException("pipeline cannot be null");
        }

        if(state == null)
        {
            throw new IllegalArgumentException("scope cannot be null");
        }

        transmogrifier = state.getTransmogrifier();

        for(final Entry<?, ?, ?> entry : pipeline)
        {
            transmogrifier.transform(transmogrifier,
                                     entry);
        }

        return null;
    }
}
