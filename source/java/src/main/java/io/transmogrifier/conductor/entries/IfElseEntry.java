package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

/**
 * @param <I>
 * @param <E>
 * @param <O>
 */
public class IfElseEntry<I, E, O>
        extends ConditionalEntry<I, E, O>
{
    /**
     *
     */
    private final Pipeline ifPipeline;

    /**
     *
     */
    private final Pipeline elsePipeline;

    /**
     * @param stat
     * @param ifPipe
     * @param cond
     */
    public IfElseEntry(final State stat,
                       final Pipeline ifPipe,
                       final String cond)
    {
        this(stat,
             ifPipe,
             null,
             cond);
    }

    /**
     * @param stat
     * @param ifPipe
     * @param elsePipe
     * @param cond
     */
    public IfElseEntry(final State stat,
                       final Pipeline ifPipe,
                       final Pipeline elsePipe,
                       final String cond)
    {
        super(stat,
              cond);

        ifPipeline = ifPipe;
        elsePipeline = elsePipe;
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
        final boolean  result;
        final Pipeline pipeline;

        result = getResult(transmogrifier);

        if(result)
        {
            pipeline = ifPipeline;
        }
        else
        {
            pipeline = elsePipeline;
        }

        if(pipeline != null)
        {
            execute(pipeline);
        }

        return null;
    }

    /**
     * @return
     */
    @Override
    public String toString()
    {
        return "ForEachEntry ? " + ifPipeline + " : " + elsePipeline;
    }
}
