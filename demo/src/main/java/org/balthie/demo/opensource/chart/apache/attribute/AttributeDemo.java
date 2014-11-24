package org.balthie.demo.opensource.chart.apache.attribute;

import org.apache.commons.attributes.Attributes;

class MyAttribute
{
    private final String ctorArg;
    
    private String namedArg = null;
    
    public MyAttribute(String ctorArg)
    {
        this.ctorArg = ctorArg;
    }
    
    public void setNamedArgument(String namedArg)
    {
        this.namedArg = namedArg;
    }
    
    public String toString()
    {
        return "[MyAttribute  constructor argument: \"" + ctorArg + "\" named argument: \"" + namedArg + "\"]";
    }
}

/**
 * @MyAttribute ("This string is passed to the constructor.",
 *               namedArgument="This argument will be passed to the setNamedArgument method")
 */
public class AttributeDemo
{
    public static void main(String args[])
    {
        System.out.println(Attributes.getAttributes(MyAttribute.class));
    }
}