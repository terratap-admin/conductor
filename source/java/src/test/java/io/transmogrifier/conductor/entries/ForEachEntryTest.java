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
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForEachEntryTest
{
    @Test
    public void test()
            throws
            FilterException
    {
        final Scope                           scope;
        final Constant<List<String>>          input;
        final Transmogrifier                  transmogrifier;
        final Conductor                       conductor;
        final State                           state;
        final Filter<String, Void, Void>      filterA;
        final FilterEntry<String, Void, Void> entryA;
        final List<Entry<?, ?, ?>>            entries;
        final Scope                           pipelineState;
        final Variable<String>                itemVariable;
        final Pipeline                        pipeline;
        final ForEachEntry<String, Void>      forEachEntry;

        scope = new Scope();
        input = scope.addConstant("str",
                                  Arrays.asList("a",
                                                "b",
                                                "c"));
        transmogrifier = new Transmogrifier();
        conductor = new Conductor();
        state = new State(transmogrifier,
                          conductor);

        // TODO: how to make this testable? It does work...
        filterA = (item, Void) ->
        {
            //            System.out.println(item);
            return null;
        };

        pipelineState = new Scope();
        itemVariable = pipelineState.addVariable("item",
                                                 null);
        entryA = new FilterEntry<>(state,
                                   filterA,
                                   itemVariable);
        entries = new ArrayList<>();
        entries.add(entryA);
        pipeline = new Pipeline(scope,
                                entries);
        forEachEntry = new ForEachEntry<>(state,
                                          pipeline,
                                          itemVariable,
                                          input);
        transmogrifier.transform(transmogrifier,
                                 forEachEntry);
    }
}
