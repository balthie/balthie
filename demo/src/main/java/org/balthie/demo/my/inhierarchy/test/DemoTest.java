package org.balthie.demo.my.inhierarchy.test;

import org.balthie.demo.my.inhierarchy.BuilderClass;
import org.balthie.demo.my.inhierarchy.SuperClass;
import org.balthie.demo.my.inhierarchy.impl.ChildClass1;

public class DemoTest
{
    public static void main(String[] args)
    {
        BuilderClass b = new BuilderClass();
        SuperClass s = b.build();
        
        System.out.println(s);
        System.out.println((ChildClass1) s);
        
        
        System.out.println(s.print());
        System.out.println(((ChildClass1) s).print());
    }
}
