package org.balthie.demo.my.inhierarchy;

import java.util.ArrayList;
import java.util.List;

public abstract class SuperClass
{
    protected Integer count;
    
    protected List<Integer> countList;
    
    protected abstract void doInitActions();
    
    void setCount(Integer count)
    {
        this.count = count;
    }
    
    void initActions()
    {
        System.out.println("SuperClass initActions this " + this);
        doInitActions();
    }
    
    protected SuperClass withList(int i)
    {
        System.out.println("SuperClass withList this " + this);
        if(countList == null) countList = new ArrayList(4);
        countList.add(i);
        
        return this;
    }
    
    public String print()
    {
        return "SuperClass [count=" + count + ", countList=" + countList + "]";
    }
}
