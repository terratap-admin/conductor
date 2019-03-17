package io.transmogrifier.conductor.entries;

import io.transmogrifier.Filter;
import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.conductor.Conductor;
import io.transmogrifier.conductor.Constant;
import io.transmogrifier.conductor.Pipeline;
import io.transmogrifier.conductor.Scope;
import io.transmogrifier.conductor.State;
import io.transmogrifier.conductor.Variable;
import io.trasnmogrifier.filter.StringFilters.UnaryLowerCaseFilter;
import io.trasnmogrifier.filter.StringFilters.UnaryUpperCaseFilter;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class PipelineEntryTest
{
    @Test
    public void test()
            throws
            FilterException
    {
        final Scope                             scope;
        final Constant<String>                  input;
        final Variable<String>                  outputA;
        final Variable<String>                  outputB;
        final Transmogrifier                    transmogrifier;
        final Conductor                         conductor;
        final State                             state;
        final Filter<String, Void, String>      filterA;
        final Filter<String, Void, String>      filterB;
        final FilterEntry<String, Void, String> entryA;
        final FilterEntry<String, Void, String> entryB;
        final List<Entry<?, ?, ?>>              entries;
        final Pipeline                          pipeline;
        final PipelineEntry                     pipelineEntry;

        scope = new Scope();
        input = scope.addConstant("str",
                                  "HellO");
        outputA = scope.addVariable("outA");
        outputB = scope.addVariable("outB");
        transmogrifier = new Transmogrifier();
        conductor = new Conductor();
        state = new State(transmogrifier,
                          conductor);
        filterA = new UnaryUpperCaseFilter();
        filterB = new UnaryLowerCaseFilter();
        entryA = new FilterEntry<>(state,
                                   filterA,
                                   input,
                                   outputA);
        entryB = new FilterEntry<>(state,
                                   filterB,
                                   input,
                                   outputB);
        entries = new ArrayList<>();
        entries.add(entryA);
        entries.add(entryB);
        pipeline = new Pipeline(scope,
                                entries);
        pipelineEntry = new PipelineEntry(state,
                                          pipeline);
        transmogrifier.transform(transmogrifier,
                                 pipelineEntry);

        assertThat(outputA.getValue(),
                   is(equalTo("HELLO")));
        assertThat(outputB.getValue(),
                   is(equalTo("hello")));
    }
}
