package io.transmogrifier.conductor;

import io.transmogrifier.Transmogrifier;

/**
 *
 */
public class State
{
    /**
     *
     */
    private final Transmogrifier transmogrifier;

    /**
     *
     */
    private final Conductor conductor;

    /**
     *
     */
    private final Scope scope;

    /**
     * @param t
     * @param c
     */
    public State(final Transmogrifier t,
                 final Conductor c)
    {
        this(t,
             c,
             new Scope());
    }

    /**
     * @param state
     * @param scp
     */
    public State(final State state,
                 final Scope scp)
    {
        this(state.getTransmogrifier(),
             state.getConductor(),
             scp);
    }

    /**
     * @param t
     * @param c
     * @param scp
     */
    public State(final Transmogrifier t,
                 final Conductor c,
                 final Scope scp)
    {
        transmogrifier = t;
        conductor = c;
        scope = scp;
    }

    /**
     * @return
     */
    public Transmogrifier getTransmogrifier()
    {
        return transmogrifier;
    }

    /**
     * @return
     */
    public Conductor getConductor()
    {
        return conductor;
    }

    /**
     * @return
     */
    public Scope getScope()
    {
        return scope;
    }
}
