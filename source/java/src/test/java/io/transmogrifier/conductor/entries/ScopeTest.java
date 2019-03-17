package io.transmogrifier.conductor.entries;

import io.transmogrifier.conductor.Scope;
import org.junit.jupiter.api.Test;

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

        scope1.addConstant("a",
                           "A");
        scope2.addConstant("b",
                           "B");
        scope3.addConstant("c",
                           "C");

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
