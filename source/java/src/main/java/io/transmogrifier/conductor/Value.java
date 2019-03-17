package io.transmogrifier.conductor;

public class Value<T>
{
    private T value;

    public Value(final T initialValue)
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

    public String toString()
    {
        return value.toString();
    }
}
