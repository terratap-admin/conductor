package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Field;
import io.transmogrifier.conductor.State;
import io.transmogrifier.conductor.Variable;

public class IfElseEntry<I, E, O>
        extends ConditionalEntry<I, E, O>
{
    private final Filter<I, E, O> ifProcessor;
    private final Field<I>        ifInputVar;
    private final Field<E>        ifExtraVar;
    private final Variable<O>     ifOutputVar;
    private final Filter<I, E, O> elseProcessor;
    private final Field<I>        elseInputVar;
    private final Field<E>        elseExtraVar;
    private final Variable<O>     elseOutputVar;

    public IfElseEntry(final String cond,
                       final Filter<I, E, O> ifProc,
                       final Field<I> ifInput,
                       final Field<E> ifExtra,
                       final Variable<O> ifOutput,
                       final Filter<I, E, O> elseProc,
                       final Field<I> elseInput,
                       final Field<E> elseExtra,
                       final Variable<O> elseOutput,
                       final State s)
    {
        super(cond,
              s);

        ifProcessor = ifProc;
        ifInputVar = ifInput;
        ifExtraVar = ifExtra;
        ifOutputVar = ifOutput;
        elseProcessor = elseProc;
        elseInputVar = elseInput;
        elseExtraVar = elseExtra;
        elseOutputVar = elseOutput;
    }

    @Override
    public Void perform(final Transmogrifier transmogrifier,
                        final Void ignore)
            throws
            FilterException
    {
        final boolean         result;
        final Filter<I, E, O> proc;
        final Field<I>        inputField;
        final Field<E>        extraField;
        final Variable<O>     outputVar;

        result = getResult(transmogrifier);

        if(result)
        {
            proc = ifProcessor;
            inputField = ifInputVar;
            extraField = ifExtraVar;
            outputVar = ifOutputVar;
        }
        else
        {
            proc = elseProcessor;
            inputField = elseInputVar;
            extraField = elseExtraVar;
            outputVar = elseOutputVar;
        }

        execute(transmogrifier,
                proc,
                inputField,
                extraField,
                outputVar);

        return null;
    }
}
