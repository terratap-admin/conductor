package io.transmogrifier.conductor;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;

public class Conductor
{
    public void conduct(final Pipeline pipeline,
                        final Transmogrifier transmogrifier)
            throws
            FilterException
    {
        final PipelineProcessor processor;

        processor = new PipelineProcessor();
        transmogrifier.transform(pipeline,
                                 transmogrifier,
                                 processor);
    }
}
