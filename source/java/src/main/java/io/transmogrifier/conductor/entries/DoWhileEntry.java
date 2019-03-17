package io.transmogrifier.conductor.entries;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.State;

public class DoWhileEntry<I, E>
        extends LoopingEntry<I, E>
{
    public DoWhileEntry(final State stat,
                        final Pipeline pipe,
                        final String cond)
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
        do
        {
            execute(pipeline);
        } while(getResult(transmogrifier));

        return null;
    }

    @Override
    public String toString()
    {
        return "DoWhileEntry: " + super.toString();
    }
}
