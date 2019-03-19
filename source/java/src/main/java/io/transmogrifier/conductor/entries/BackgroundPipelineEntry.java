package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 *
 */
public class BackgroundPipelineEntry
        extends PipelineEntry
{
    private final ExecutorService executorService;

    /**
     * @param stat
     * @param pipe
     */
    public BackgroundPipelineEntry(final State stat,
                                   final Pipeline pipe,
                                   final ExecutorService service)
    {
        super(stat,
              pipe);

        executorService = service;
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
        final Future<?> future;

        future = executorService.submit(() ->
                               {
                                   try
                                   {
                                       super.perform(transmogrifier);
                                   }
                                   catch(final FilterException ex)
                                   {
                                       throw new RuntimeException(ex.getMessage(),
                                                                  ex);
                                   }
                               });

        return null;
    }

    /**
     * @return
     */
    @Override
    public String toString()
    {
        return "BackgroundPipeline";
    }
}
