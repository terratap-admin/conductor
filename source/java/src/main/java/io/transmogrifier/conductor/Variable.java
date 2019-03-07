package io.transmogrifier.conductor;

public class Variable<T>
        extends Field<T>
{
    public Variable(final String nm)
    {
        super(nm,
              null);
    }

    public Variable(final String nm,
                    final T initialValue)
    {
        super(nm,
              initialValue);
    }

    public void setValue(final T value)
    {
        variableWrapper.setValue(value);
    }
}