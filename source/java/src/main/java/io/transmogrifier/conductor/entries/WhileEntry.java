package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

public class WhileEntry<I, E>
        extends LoopingEntry<I, E>
{
    public WhileEntry(final State stat,
                      final String cond,
                      final Pipeline pipe)
    {
        super(stat,
              pipe,
              cond);
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void ignore)
            throws
            FilterException
    {
        while(getResult(transmogrifier))
        {
            execute(pipeline);
        }

        return null;
    }

    @Override
    public String toString()
    {
        return "WhileEntry: " + super.toString();
    }
}
