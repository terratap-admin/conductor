package io.transmogrifier.conductor;

public class VariableWrapper<T>
{
    private T value;

    public VariableWrapper(final T initialValue)
    {
        value = initialValue;
    }

    public T getValue()
    {
        return value;
    }

    public void setValue(final T val)
    {
        value = val;
    }
}
