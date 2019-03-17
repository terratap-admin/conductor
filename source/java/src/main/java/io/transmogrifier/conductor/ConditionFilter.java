package io.transmogrifier.conductor;

import com.udojava.evalex.Expression;
import io.transmogrifier.UnaryFilter;

import java.math.BigDecimal;

/**
 *
 */
public class ConditionFilter
        implements UnaryFilter<String, Boolean>
{
    /**
     * @param expressionString
     * @return
     */
    @Override
    public Boolean perform(final String expressionString)
    {
        final Expression expression;
        final BigDecimal result;
        final boolean    retVal;

        expression = new Expression(expressionString);
        result = expression.eval();
        retVal = !(result.equals(BigDecimal.ZERO));

        return retVal;
    }
}
