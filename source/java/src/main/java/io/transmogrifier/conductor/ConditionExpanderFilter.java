package io.transmogrifier.conductor;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class ConditionExpanderFilter
        implements Filter<String, Scope, String>
{
    /**
     *
     */
    private static final Pattern FIELD_PATTERN;

    static
    {
        FIELD_PATTERN = Pattern.compile("\\$\\(\\w*\\)");
    }

    /**
     * @param expression
     * @param state
     * @return
     * @throws FilterException
     */
    @Override
    public String perform(final String expression,
                          final Scope state)
            throws
            FilterException
    {
        String  currentValue;
        Matcher matcher;

        if(expression == null)
        {
            return null;
        }

        currentValue = expression;
        matcher = FIELD_PATTERN.matcher(currentValue);

        while(matcher.find())
        {
            final String   group;
            final String   fieldName;
            final Field<?> field;
            final Object   value;

            group = matcher.group();
            fieldName = extractValue(group);
            field = state.getField(fieldName);

            if(field == null)
            {
                throw new FilterException(fieldName + " does not exist");
            }

            value = field.getValue();
            currentValue = currentValue.replace(group,
                                                value.toString());
        }

        return currentValue;
    }


    /**
     * @param str
     * @return
     */
    private String extractValue(final String str)
    {
        final String value;

        value = str.substring(2,
                              str.length() - 1);

        return value;
    }
}
