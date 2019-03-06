package io.transmogrifier.conductor;

public class Field<T>
{
    protected final VariableWrapper<T> variableWrapper;
    private final   String             name;

    Field(final String nm,
          final T initialValue)
    {
        if(nm == null)
        {
            throw new IllegalArgumentException("nm cannot be null");
        }

        name = nm;
        variableWrapper = new VariableWrapper<>(initialValue);
    }

    public String getName()
    {
        return name;
    }

    public T getValue()
    {
        final T value;

        value = variableWrapper.getValue();

        return value;
    }
}
