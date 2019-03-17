package io.transmogrifier.conductor;

import io.transmogrifier.Transmogrifier;

public class State
{
    private final Transmogrifier transmogrifier;
    private final Conductor      conductor;
    private final Scope          scope;

    public State(final Transmogrifier t,
                 final Conductor c)
    {
        this(t,
             c,
             new Scope());
    }

    public State(final State state,
                 final Scope scp)
    {
        this(state.getTransmogrifier(),
             state.getConductor(),
             scp);
    }

    public State(final Transmogrifier t,
                 final Conductor c,
                 final Scope scp)
    {
        transmogrifier = t;
        conductor = c;
        scope = scp;
    }

    public Transmogrifier getTransmogrifier()
    {
        return transmogrifier;
    }

    public Conductor getConductor()
    {
        return conductor;
    }

    public Scope getScope()
    {
        return scope;
    }
}
