package io.transmogrifier.conductor;

/**
 * @param <T>
 */
public class Constant<T>
        extends Field<T>
{
    /**
     * @param nm
     * @param initialValue
     */
    public Constant(final String nm,
                    final T initialValue)
    {
        super(nm,
              initialValue);
    }

    /**
     * @return
     */
    public String toString()
    {
        return "const " + super.toString();
    }
}
