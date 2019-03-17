package io.transmogrifier.conductor;

/**
 * @param <T>
 */
public class Field<T>
{
    /**
     *
     */
    protected final Value<T> value;

    /**
     *
     */
    private final String name;

    /**
     * @param nm
     * @param initialValue
     */
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

    /**
     * @return
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return
     */
    public T getValue()
    {
        final T val;

        val = value.getValue();

        return val;
    }

    /**
     * @return
     */
    public String toString()
    {
        return name + " = " + value;
    }
}
