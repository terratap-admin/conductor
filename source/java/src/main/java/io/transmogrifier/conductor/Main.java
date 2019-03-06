package io.transmogrifier.conductor;

import io.transmogrifier.FilterException;
import io.transmogrifier.Transmogrifier;
import io.transmogrifier.UnaryFilter;
import io.transmogrifier.conductor.entries.IfElseEntry;
import io.transmogrifier.conductor.entries.PipelineEntry;
import io.transmogrifier.conductor.entries.StandardEntry;

import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
            throws
            FilterException
    {
        final State                        state;
        final List<PipelineEntry<?, ?, ?>> entries;
        final Pipeline                     pipeline;
        final Conductor                    conductor;
        final Transmogrifier               transmogrifier;

        transmogrifier = new Transmogrifier();
        state = createState(transmogrifier);
        entries = createEntries(state);
        pipeline = new Pipeline(entries);
        conductor = new Conductor();
        conductor.conduct(pipeline,
                          transmogrifier);
    }

    private static State createState(final Transmogrifier transmogrifier)
    {
        final State state;

        state = new State();
        state.addConstant("transmogrifier",
                          transmogrifier);
        state.addConstant("in",
                          "hELLo");
        state.addVariable("out",
                          null);
        state.addConstant("condA",
                          false);
        state.addConstant("condB",
                          false);

        return state;
    }

    @SuppressWarnings("unchecked")
    private static List<PipelineEntry<?, ?, ?>> createEntries(final State state)
    {
        final List<PipelineEntry<?, ?, ?>> entries;

        entries = new ArrayList<>();

        entries.add(new IfElseEntry("$(condA)",
                                      new EntryFilter<>(new IfElseEntry<>("$(condB)",
                                                        new UppercaseFilter(),
                                                        state.getField("in"),
                                                        null,
                                                        state.getVariable("out"),
                                                        new UppercaseFirstFilter(),
                                                        state.getField("in"),
                                                        null,
                                                        state.getVariable("out"),
                                                        state)),
                                       state.getField("transmogrifier"),
                                      null,
                                      null,
                                      new LowercaseFilter(),
                                      state.getField("in"),
                                      null,
                                      state.getVariable("out"),
                                      state));
        entries.add(new StandardEntry<>(new PrintFilter<>(),
                                        state.getField("out"),
                                        null,
                                        null));

        return entries;
    }
}

class UppercaseFilter
        implements UnaryFilter<String, String>
{
    @Override
    public String perform(final String input)
    {
        return input.toUpperCase();
    }
}

class LowercaseFilter
        implements UnaryFilter<String, String>
{
    @Override
    public String perform(final String input)
    {
        return input.toLowerCase();
    }
}

class UppercaseFirstFilter
        implements UnaryFilter<String, String>
{
    @Override
    public String perform(final String input)
    {
        final String remainder;
        final String retVal;

        remainder = input.substring(1).toLowerCase();
        retVal = Character.toUpperCase(input.charAt(0)) + remainder;

        return retVal;
    }
}

class PrintFilter<T>
        implements UnaryFilter<T, Void>
{
    @Override
    public Void perform(final T input)
    {
        System.out.println(input);
        return null;
    }
}
