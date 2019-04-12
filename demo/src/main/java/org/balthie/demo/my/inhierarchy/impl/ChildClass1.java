package org.balthie.demo.my.inhierarchy.impl;

import org.balthie.demo.my.inhierarchy.SuperClass;

public class ChildClass1 extends SuperClass
{
    public String print()
    {
        return "ChildClass1 [count=" + count + ", countList=" + countList + "]";
    }
    
    @Override
    protected void doInitActions()
    {
        System.out.println("ChildClass1 doInitActions this " + this);
        this.withList(1);
        this.withList(2);
    }
    
}
