/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月9日 上午10:46:51
 * @description TODO 一句话描述
 */
package org.balthie.demo.my.clazz.inner;

/**
 * @author：balthie + 10050
 * @createtime ： 2015年3月9日 上午10:46:51
 * @description 测试内部类对象创建时机
 */
public class Outer1
{
    public Outer1()
    {
        System.out.println("Outer1 created");
    }
    
    class InnerClass
    {
        public InnerClass()
        {
            System.out.println("InnerClass created");
        }
    }
    
    static class InnerStaticClass
    {
        static Outer1 instance = new Outer1();
        
        // 避免静态类的实例化
        private InnerStaticClass()
        {
            System.out.println("InnerStaticClass created");
        }
    }
    
    public static void main(String[] args)
    {
        // 单例的最佳实现，使用类加载机制，避免了使用new操作在多线程环境带来的同步性问题
        // 调用 InnerStaticClass.instance时，首次加载和创建 Outer1 对象
        System.out.println(InnerStaticClass.instance);
        
        InnerStaticClass s = new InnerStaticClass();
        
        
    }
}
