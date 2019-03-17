package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Conductor;
import io.transmogrifier.conductor.Constant;
import io.transmogrifier.conductor.Scope;
import io.transmogrifier.conductor.State;
import io.transmogrifier.conductor.Variable;
import io.trasnmogrifier.filter.StringFilters.UnaryUpperCaseFilter;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FilterEntryTest
{
    @Test
    public void test()
            throws
            FilterException
    {
        final Scope                             scope;
        final Constant<String>                  input;
        final Variable<String>                  output;
        final Transmogrifier                    transmogrifier;
        final Conductor                         conductor;
        final State                             state;
        final Filter<String, Void, String>      filter;
        final FilterEntry<String, Void, String> entry;

        scope = new Scope();
        input = scope.addConstant("str",
                                  "Hello");
        output = scope.addVariable("out");
        transmogrifier = new Transmogrifier();
        conductor = new Conductor();
        state = new State(transmogrifier,
                          conductor);
        filter = new UnaryUpperCaseFilter();
        entry = new FilterEntry<>(state,
                                  filter,
                                  input,
                                  output);
        transmogrifier.transform(transmogrifier,
                                 entry);

        assertThat(output.getValue(),
                   is(equalTo("HELLO")));
    }
}
