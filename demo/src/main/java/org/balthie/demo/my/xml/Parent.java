package org.balthie.demo.my.xml;

import java.util.ArrayList;
import java.util.List;

public class Parent
{
    private String name;
    
    private List<Child> childs;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public List<Child> getChilds()
    {
        return childs;
    }
    
    public void setChilds(List<Child> childs)
    {
        this.childs = childs;
    }
    
    public void addChild(Child c)
    {
        if(childs == null) childs = new ArrayList<Child>();
        childs.add(c);
    }
}
