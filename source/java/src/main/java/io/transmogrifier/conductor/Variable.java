package io.transmogrifier.conductor;

/**
 * @param <T>
 */
public class Variable<T>
        extends Field<T>
{
    /**
     * @param nm
     */
    public Variable(final String nm)
    {
        super(nm,
              null);
    }

    /**
     * @param nm
     * @param initialValue
     */
    public Variable(final String nm,
                    final T initialValue)
    {
        super(nm,
              initialValue);
    }

    /**
     * @param value
     */
    public void setValue(final T value)
    {
        this.value.setValue(value);
    }

    /**
     * @return
     */
    public String toString()
    {
        return "var " + super.toString();
    }
}
