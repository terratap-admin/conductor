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

public class ScopeTest
{
    @Test
    public void test()
    {
        final Scope scope1;
        final Scope scope2;
        final Scope scope3;

        scope1 = new Scope();
        scope2 = new Scope(scope1);
        scope3 = new Scope(scope2);

        scope1.addConstant("a", "A");
        scope2.addConstant("b", "B");
        scope3.addConstant("c", "C");

        System.out.println(scope1);
        System.out.println("---");
        System.out.println(scope2);
        System.out.println("---");
        System.out.println(scope3);

        System.out.println(scope3.getField("a"));
        System.out.println(scope3.getField("b"));
        System.out.println(scope3.getField("c"));
    }
}
