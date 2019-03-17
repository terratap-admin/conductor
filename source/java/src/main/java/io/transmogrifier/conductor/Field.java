package io.transmogrifier.conductor;

public class Field<T>
{
    protected final Value<T> value;
    private final   String   name;

    Field(final String nm,
          final T initialValue)
    {
        if(nm == null)
        {
            throw new IllegalArgumentException("nm cannot be null");
        }

        name = nm;
        value = new Value<>(initialValue);
    }

    public String getName()
    {
        return name;
    }

    public T getValue()
    {
        final T val;

        val = value.getValue();

        return val;
    }

    public String toString()
    {
        return name + " = " + value;
    }
}
