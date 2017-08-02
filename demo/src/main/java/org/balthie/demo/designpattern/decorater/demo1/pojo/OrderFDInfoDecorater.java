package org.balthie.demo.designpattern.decorater.demo1.pojo;

public class OrderFDInfoDecorater implements OrderInfoDecorater
{
    private String invoiceTitle;
    
    private Byte invoiceType;
    
    private Byte invoiceStatus;
    
    private Order order;
    
    public OrderFDInfoDecorater(Order order)
    {
        super();
        this.order = order;
        decorate();
    }
    
    void decorate()
    {
        this.invoiceTitle = "title";
        this.invoiceType = 1;
        this.invoiceStatus = 1;
    }
    
    public String getInvoiceTitle()
    {
        return invoiceTitle;
    }
    
    public void setInvoiceTitle(String invoiceTitle)
    {
        this.invoiceTitle = invoiceTitle;
    }
    
    public Byte getInvoiceType()
    {
        return invoiceType;
    }
    
    public void setInvoiceType(Byte invoiceType)
    {
        this.invoiceType = invoiceType;
    }
    
    public Byte getInvoiceStatus()
    {
        return invoiceStatus;
    }
    
    public void setInvoiceStatus(Byte invoiceStatus)
    {
        this.invoiceStatus = invoiceStatus;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("OrderFDInfoDecorater [invoiceTitle=").append(invoiceTitle).append(", invoiceType=").append(invoiceType)
                .append(", invoiceStatus=").append(invoiceStatus).append(", order=").append(order).append("]");
        return builder.toString();
    }
}
