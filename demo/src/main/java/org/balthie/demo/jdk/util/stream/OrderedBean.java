package org.balthie.demo.jdk.util.stream;

public class OrderedBean
{
    public OrderedBean(int order)
    {
        super();
        this.order = order;
    }
    
    int order;
    
    public int getOrder()
    {
        return order;
    }
    
    public void setOrder(int order)
    {
        this.order = order;
    }
    
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderedBean [order=").append(order).append("]");
        return builder.toString();
    }
}
