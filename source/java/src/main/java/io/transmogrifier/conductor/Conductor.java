package io.transmogrifier.conductor;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;

/**
 *
 */
public class Conductor
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
        final PipelineFilter filter;
        final Transmogrifier transmogrifier;

        if(pipeline == null)
        {
            throw new RuntimeException("pipeline cannot be null");
        }

        if(state == null)
        {
            throw new RuntimeException("scope cannot be null");
        }

        filter = new PipelineFilter();
        transmogrifier = state.getTransmogrifier();
        transmogrifier.transform(pipeline,
                                 state,
                                 filter);

        return null;
    }
}
