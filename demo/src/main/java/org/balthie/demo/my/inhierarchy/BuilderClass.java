package org.balthie.demo.my.inhierarchy;

import org.balthie.demo.my.inhierarchy.impl.ChildClass1;

public class BuilderClass
{
    public SuperClass build()
    {
        SuperClass s = new ChildClass1();
        s.initActions();
        s.setCount(100);
        System.out.println("BuilderClass withList this " + s);
        s.withList(3).withList(4);
        return s;
    }
    
}
