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
import static org.hamcrest.Matchers.nullValue;

public class IfElseEntryTest
{
    @Test
    public void testIfElseTrue()
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
        final List<Entry<?, ?, ?>>              entriesA;
        final List<Entry<?, ?, ?>>              entriesB;
        final Pipeline                          pipelineA;
        final Pipeline                          pipelineB;
        final IfElseEntry                       ifElseEntry;

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
        entriesA = new ArrayList<>();
        entriesA.add(entryA);
        entriesB = new ArrayList<>();
        entriesB.add(entryB);
        pipelineA = new Pipeline(scope,
                                 entriesA);
        pipelineB = new Pipeline(scope,
                                 entriesB);
        ifElseEntry = new IfElseEntry(state,
                                      pipelineA,
                                      pipelineB,
                                      "true");
        transmogrifier.transform(transmogrifier,
                                 ifElseEntry);

        assertThat(outputA.getValue(),
                   is(equalTo("HELLO")));
        assertThat(outputB.getValue(),
                   is(nullValue()));
    }

    @Test
    public void testIfElseFalse()
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
        final List<Entry<?, ?, ?>>              entriesA;
        final List<Entry<?, ?, ?>>              entriesB;
        final Pipeline                          pipelineA;
        final Pipeline                          pipelineB;
        final IfElseEntry                       ifElseEntry;

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
        entriesA = new ArrayList<>();
        entriesA.add(entryA);
        entriesB = new ArrayList<>();
        entriesB.add(entryB);
        pipelineA = new Pipeline(scope,
                                 entriesA);
        pipelineB = new Pipeline(scope,
                                 entriesB);
        ifElseEntry = new IfElseEntry(state,
                                      pipelineA,
                                      pipelineB,
                                      "false");
        transmogrifier.transform(transmogrifier,
                                 ifElseEntry);

        assertThat(outputA.getValue(),
                   is(nullValue()));
        assertThat(outputB.getValue(),
                   is(equalTo("hello")));
    }

    @Test
    public void testIfTrue()
            throws
            FilterException
    {
        final Scope                             scope;
        final Constant<String>                  input;
        final Variable<String>                  outputA;
        final Transmogrifier                    transmogrifier;
        final Conductor                         conductor;
        final State                             state;
        final Filter<String, Void, String>      filterA;
        final FilterEntry<String, Void, String> entryA;
        final List<Entry<?, ?, ?>>              entriesA;
        final Pipeline                          pipelineA;
        final IfElseEntry                       ifElseEntry;

        scope = new Scope();
        input = scope.addConstant("str",
                                  "HellO");
        outputA = scope.addVariable("outA");
        transmogrifier = new Transmogrifier();
        conductor = new Conductor();
        state = new State(transmogrifier,
                          conductor);
        filterA = new UnaryUpperCaseFilter();
        entryA = new FilterEntry<>(state,
                                   filterA,
                                   input,
                                   outputA);
        entriesA = new ArrayList<>();
        entriesA.add(entryA);
        pipelineA = new Pipeline(scope,
                                 entriesA);
        ifElseEntry = new IfElseEntry(state,
                                      pipelineA,
                                      "true");
        transmogrifier.transform(transmogrifier,
                                 ifElseEntry);

        assertThat(outputA.getValue(),
                   is(equalTo("HELLO")));
    }

    @Test
    public void testIfFalse()
            throws
            FilterException
    {
        final Scope                             scope;
        final Constant<String>                  input;
        final Variable<String>                  outputA;
        final Transmogrifier                    transmogrifier;
        final Conductor                         conductor;
        final State                             state;
        final Filter<String, Void, String>      filterA;
        final FilterEntry<String, Void, String> entryA;
        final List<Entry<?, ?, ?>>              entriesA;
        final Pipeline                          pipelineA;
        final IfElseEntry                       ifElseEntry;

        scope = new Scope();
        input = scope.addConstant("str",
                                  "HellO");
        outputA = scope.addVariable("out");
        transmogrifier = new Transmogrifier();
        conductor = new Conductor();
        state = new State(transmogrifier,
                          conductor);
        filterA = new UnaryUpperCaseFilter();
        entryA = new FilterEntry<>(state,
                                   filterA,
                                   input,
                                   outputA);
        entriesA = new ArrayList<>();
        entriesA.add(entryA);
        pipelineA = new Pipeline(scope,
                                 entriesA);
        ifElseEntry = new IfElseEntry(state,
                                      pipelineA,
                                      "false");
        transmogrifier.transform(transmogrifier,
                                 ifElseEntry);

        assertThat(outputA.getValue(),
                   is(nullValue()));
    }
}
