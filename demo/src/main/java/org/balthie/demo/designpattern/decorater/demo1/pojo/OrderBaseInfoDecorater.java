package org.balthie.demo.designpattern.decorater.demo1.pojo;

public class OrderBaseInfoDecorater implements OrderInfoDecorater
{
    private String code;
    
    private Order order;
    
    public OrderBaseInfoDecorater(Order order)
    {
        super();
        this.order = order;
        decorate();
    }
    
    void decorate()
    {
        this.code = "124";
    }
    
    public String getCode()
    {
        return code;
    }
    
    public void setCode(String code)
    {
        this.code = code;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderBaseInfoDecorater [code=").append(code).append(", order=").append(order).append("]");
        return builder.toString();
    }
}
