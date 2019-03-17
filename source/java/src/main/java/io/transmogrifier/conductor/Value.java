package io.transmogrifier.conductor;

/**
 * @param <T>
 */
public class Value<T>
{
    /**
     *
     */
    private T value;

    public Value(final T initialValue)
    {
        value = initialValue;
    }

    /**
     * @return
     */
    public T getValue()
    {
        return value;
    }

    /**
     * @param val
     */
    public void setValue(final T val)
    {
        value = val;
    }

    /**
     * @return
     */
    public String toString()
    {
        return value.toString();
    }
}
