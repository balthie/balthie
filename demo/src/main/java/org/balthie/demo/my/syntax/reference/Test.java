/**
 * @author：balthie + 10050
 * @createtime ： 2015年2月10日 上午10:05:06
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.syntax.reference;

import java.util.Date;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年2月10日 上午10:05:06
 * @description 测试引用传递
 */
public class Test
{
    public static void main(String[] args)
    {
        testPassReference();
    }
    
    public static void testPassReference()
    {
        Date date = new Date();
        
        System.out.println(" 初始化参数对象 "+date);
        cleanReferenceMethod(date);
        System.out.println(" 清空方法入参的引用，不能影响方法调用者 "+date);
        modifyReferenceMethod(date);
        System.out.println("修改方法入参的内容，能影响方法调用者 "+date);
    }
    
    // 清空方法入参的引用，不影响方法调用者 ： 原因是方法体中，date是一个新的局部变量
    private static void cleanReferenceMethod(Date date)
    {
        date = null;
    }
    
    // 修改方法入参的内容，能影响方法调用者
    private static void modifyReferenceMethod(Date date)
    {
        date.setTime(100000000L);
    }
}
