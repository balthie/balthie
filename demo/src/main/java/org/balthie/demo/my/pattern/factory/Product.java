/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月27日 下午2:37:07
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.pattern.factory;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年5月27日 下午2:37:07
 * @description 静态内部类工厂
 */
public class Product
{
    static
    {
        System.out.println(" Product Class be loaded ");
    }
    {
        System.out.println(" Product be initialed ");
    }
    
    private Product()
    {
        super();
        System.out.println(" Product construct invoke! ");
    }
    
    public static class StaticFactory
    {
        // 静态内部类，在第一次被调用时，被classloader加载
        static
        {
            System.out.println(" StaticFactory class be loaded ");
        }
        
        {
            System.out.println(" StaticFactory be initialed ");
        }
        
        private static Product instance = new Product();
        
        public static Product getInstance()
        {
            System.out.println(" StaticFactory getInstance invoke ");
            return instance;
        }
    }
    
    public static void main(String[] args)
    {
        System.out.println(" StaticFactory getInstance invoke from outter");
        StaticFactory.getInstance();
    }
}
