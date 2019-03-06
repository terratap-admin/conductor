package io.transmogrifier.conductor;

public class Constant<T>
        extends Field<T>
{
    public Constant(final String nm,
                    final T initialValue)
    {
        super(nm,
              initialValue);
    }
}
