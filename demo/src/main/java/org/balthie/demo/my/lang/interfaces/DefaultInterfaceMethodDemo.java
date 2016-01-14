package org.balthie.demo.my.lang.interfaces;

// http://developer.51cto.com/art/201404/435217.htm
// 
interface DefaultInterfaceMethodDemo
{
    // 定义一个方法，如果子类不实现它，就报不支持的异常
    default void dosomething()
    {
        throw new UnsupportedOperationException();
    }
}
