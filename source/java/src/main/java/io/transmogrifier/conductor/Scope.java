package io.transmogrifier.conductor;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Scope
{
    private final Scope                               outer;
    private final ConcurrentHashMap<String, Field<?>> constants;
    private final ConcurrentHashMap<String, Field<?>> variables;
    private final ConcurrentHashMap<String, Field<?>> fields;

    {
        constants = new ConcurrentHashMap<>();
        variables = new ConcurrentHashMap<>();
        fields = new ConcurrentHashMap<>();
    }

    public Scope()
    {
        this(null);
    }

    public Scope(final Scope o)
    {
        outer = o;
    }

    public <T> Constant<T> addConstant(final String name,
                                       final T initalValue)
    {
        final Constant<T> constant;

        constant = new Constant<>(name,
                                  initalValue);
        addConstant(constant);

        return constant;
    }

    public <T> void addConstant(final Constant<T> constant)
    {
        addField(constant);
        addField(constant,
                 constants);
    }

    public <T> Variable<T> addVariable(final String name)
    {
        final Variable<T> variable;

        variable = addVariable(name,
                               null);

        return variable;
    }

    public <T> Variable<T> addVariable(final String name,
                                       final T initalValue)
    {
        final Variable<T> variable;

        variable = new Variable<>(name,
                                  initalValue);
        addVariable(variable);

        return variable;
    }

    public <T> void addVariable(final Variable<T> variable)
    {
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

    public <T> T getValue(final String name)
    {
        final Field<T> field;
        final T value;

        field = getField(name);

        if(field == null)
        {
            throw new RuntimeException("no such field: " + name);
        }
        value = field.getValue();

        return value;
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
        Field<T> field;

        field = (Field<T>)fieldMap.get(name);

        if(field == null && outer != null)
        {
            field = outer.getField(name);
        }

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

        if(field == null && outer != null)
        {
            final Field<?> existingField;

            existingField = outer.getField(name);

            if(existingField != null)
            {
                throw new RuntimeException("shadowed field: " + name);
            }
        }

        oldValue = fieldMap.putIfAbsent(name,
                                        field);

        if(oldValue != null)
        {
            throw new RuntimeException("duplicate field: " + name);
        }
    }

    public String toString()
    {
        final StringBuilder builder;

        builder = new StringBuilder();
        toString(this, builder);

        // get rid of the trailing \n
        builder.setLength(builder.length() - 1);

        return builder.toString();
    }

    private int toString(final Scope scope, final StringBuilder builder)
    {
        final Scope outer;
        final int depth;

        outer = scope.outer;

        if(outer == null)
        {
            appendOpen(builder, 0);
            appendFields(scope.fields.values(), builder, 0);
            appendClose(builder, 0);

            return 0;
        }

        depth = toString(outer, builder) + 1;
        appendOpen(builder, depth);
        appendFields(scope.fields.values(), builder, depth);
        appendClose(builder, depth);

        return depth;
    }

    private void appendFields(final Collection<Field<?>> fields,
                              final StringBuilder builder,
                              final int depth)
    {
        for(final Field<?> field : fields)
        {
            appendField(field, builder, depth + 1);
        }
    }

    private void appendField(final Field<?> field,
                             final StringBuilder builder,
                             final int depth)
    {
        appendSpaces(builder, depth);
        builder.append(field.getName());
        builder.append(" = ");
        builder.append(field.getValue());
        builder.append("\n");
    }

    private void appendOpen(final StringBuilder builder,
                            final int depth)
    {
        appendSpaces(builder, depth);
        builder.append("{\n");
    }
    private void appendClose(final StringBuilder builder,
                             final int depth)
    {
        appendSpaces(builder, depth);
        builder.append("}\n");
    }

    private void appendSpaces(final StringBuilder builder,
                              final int depth)
    {
        if(depth > 0)
        {
            builder.append(String.format("%" + depth + "s",
                                         ""));
        }
    }
}
