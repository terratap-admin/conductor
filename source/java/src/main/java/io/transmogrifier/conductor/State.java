package io.transmogrifier.conductor;

import java.util.concurrent.ConcurrentHashMap;

public class State
{
    private final ConcurrentHashMap<String, Field<?>> constants;
    private final ConcurrentHashMap<String, Field<?>> variables;
    private final ConcurrentHashMap<String, Field<?>> fields;

    {
        constants = new ConcurrentHashMap<>();
        variables = new ConcurrentHashMap<>();
        fields = new ConcurrentHashMap<>();
    }

    public <T> void addConstant(final String name,
                                final T initalValue)
    {
        final Constant<T> constant;

        constant = new Constant<>(name,
                                  initalValue);
        addField(constant);
        addField(constant,
                 constants);
    }

    public <T> void addVariable(final String name,
                                final T initalValue)
    {
        final Variable<T> variable;

        variable = new Variable<>(name,
                                  initalValue);
        addField(variable);
        addField(variable,
                 variables);
    }

    public <T> Constant<T> getConstant(final String name)
    {
        final Constant<T> constant;

        constant = (Constant<T>)getField(name,
                                         constants);

        return (constant);
    }

    public <T> Variable<T> getVariable(final String name)
    {
        final Variable<T> variable;

        variable = (Variable<T>)getField(name,
                                         variables);

        return (variable);
    }

    public <T> Field<T> getField(final String name)
    {
        final Field<T> field;

        field = (Field<T>)getField(name,
                                   fields);

        return (field);
    }

    private <T> Field<T> getField(final String name,
                                  final ConcurrentHashMap<String, Field<?>> fieldMap)
    {
        final Field<T> field;

        field = (Field<T>)fieldMap.get(name);

        return (field);
    }

    private <T> void addField(final Field<T> field)
    {
        addField(field,
                 fields);
    }

    private void addField(final Field<?> field,
                          final ConcurrentHashMap<String, Field<?>> fieldMap)
    {
        final Field<?> oldValue;
        final String   name;

        name = field.getName();
        oldValue = fieldMap.putIfAbsent(name,
                                        field);

        if(oldValue != null)
        {
            throw new RuntimeException("duplicate constant: " + name);
        }
    }
}
