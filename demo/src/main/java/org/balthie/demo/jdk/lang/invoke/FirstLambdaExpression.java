package org.balthie.demo.jdk.lang.invoke;

public class FirstLambdaExpression
{
    public String variable = "Class Level Variable";
    
    public static void main(String[] arg)
    {
        new FirstLambdaExpression().lambdaExpression();
        
        new FirstLambdaExpression().anonymousExample();
    }
    
    // 匿名类到处都在使用，但是他们还是有很多问题。
    // 第一个主要问题是复杂。这些类让代码的层级看起来很乱很复杂，也称作 Vertical Problem 。
    // 第二，匿名类不能访问封装类的非 final 成员。this
    // 这个关键字将变得很有迷惑性。如果一个匿名类有一个与其封装类相同的成员名称，内部变量将会覆盖外部的成员变量，在这种情况下，外部的成员在匿名类内部将是不可见的，甚至不能通过 this
    // 关键字来访问。因为 this 关键字值得是匿名类对象本身而不是他的封装类的对象。
    public void anonymousExample()
    {
        String nonFinalVariable = "Non Final Example";
        String variable = "Outer Method Variable";
        new Thread(new Runnable() {
            String variable = "Runnable Class Member";
            
            public void run()
            {
                String variable = "Run Method Variable";
                // Below line gives compilation error.
                // System.out.println("->" + nonFinalVariable);
                System.out.println("->" + variable);
                System.out.println("->" + this.variable);
            }
        }).start();
    }
    
    public void lambdaExpression()
    {
        // 使用lambda 可以避免匿名类的问题
        String variable = "Method Local Variable";
        String nonFinalVariable = "This is non final variable";
        new Thread(() -> {
            // Below line gives compilation error
            // String variable = "Run Method Variable"
                System.out.println("->" + variable);
                System.out.println("->" + this.variable);
            }).start();
    }
}