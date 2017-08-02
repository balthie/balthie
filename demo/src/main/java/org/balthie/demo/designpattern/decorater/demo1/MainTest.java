package org.balthie.demo.designpattern.decorater.demo1;

import org.balthie.demo.designpattern.decorater.demo1.pojo.Order;
import org.balthie.demo.designpattern.decorater.demo1.pojo.OrderBaseInfoDecorater;
import org.balthie.demo.designpattern.decorater.demo1.pojo.OrderFDInfoDecorater;
import org.balthie.demo.designpattern.decorater.demo1.pojo.UserOrder;

public class MainTest
{
    public static void main(String[] args)
    {
        Order uo = new OrderBaseInfoDecorater(new UserOrder());
        System.out.println(uo.toString());
        
        uo = new OrderFDInfoDecorater(uo);
        System.out.println(uo.toString());
        
    }
}
