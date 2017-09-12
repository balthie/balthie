package org.balthie.demo.jvm.keyword;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//http://www.importnew.com/18126.html

// volatile 对于内存事务一致性的作用
// 1 原子性 （这种操作一旦开始，就一直运行到结束，中间不会有任何 context switch）    不保证
// 2 可见性(隔离性)  线程1对变量i修改了之后，线程2可以立即看到线程1修改的值。               保证
// 3 顺序性（原子性）  --> 指令重排, 指令重排序不会影响单个线程的执行，但是会影响到线程并发执行的正确性。  volatile会禁止进行指令重排序，能在一定程度上保证有序性
// 3.1 当程序执行到volatile变量的读操作或者写操作时，在其前面的操作的更改肯定全部已经进行，且结果已经对后面的操作可见；在其后面的操作肯定还没有进行；
// 3.2 在进行指令优化时，不能将在对volatile变量访问的语句放在其后面执行，也不能把volatile变量后面的语句放到其前面执行。

// 底层原理
/*
 * 下面这段话摘自《深入理解Java虚拟机》：
 * “观察加入volatile关键字和没有加入volatile关键字时所生成的汇编代码发现，加入volatile关键字时，会多出一个lock前缀指令”
 * lock前缀指令实际上相当于一个内存屏障（也成内存栅栏），内存屏障会提供3个功能：
 * 1）它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；即在执行到内存屏障这句指令时，在它前面的操作已经全部完成；
 * 2）它会强制将对缓存的修改操作立即写入主存；
 * 3）如果是写操作，它会导致其他CPU中对应的缓存行无效。
 */
public class VolatileDemo
{
    // 由于语句1和语句2没有数据依赖性，因此可能会被重排序。假如发生了重排序，在线程1执行过程中先执行语句2，而此是线程2会以为初始化工作已经完成，那么就会跳出while循环，去执行doSomethingwithconfig(context)方法，而此时context并没有被初始化，就会导致程序出错。
    
    // 如果变量值被 volatile修饰
    // 第一：使用volatile关键字会强制将修改的值立即写入主存；
    // 第二：使用volatile关键字的话，当线程2进行修改时，会导致线程1的工作内存中缓存变量stop的缓存行无效（反映到硬件层的话，就是CPU的L1或者L2缓存中对应的缓存行无效）；
    // 第三：由于线程1的工作内存中缓存变量stop的缓存行无效，所以线程1再次读取变量stop的值时会去主存读取。
    public static void orderResort()
    {
        // 线程1:
        // context = loadContext(); //语句1
        // inited = true; //语句2
        
        // 线程2:
        // while(!inited ){
        // sleep()
        // }
        // doSomethingwithconfig(context);
    }
    
    public static void nonAtomic()
    {
        final Test test = new Test();
        for(int i = 0; i < 10; i++)
        {
            new Thread() {
                public void run()
                {
                    for(int j = 0; j < 1000; j++)
                        test.increase();
                };
            }.start();
        }
        
        while (Thread.activeCount() > 1) // 保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }
    
    public static class Test
    {
        public volatile int inc = 0;
        
        public void increase()
        {
            // 自增操作是不具备原子性的，它包括读取变量的原始值、进行加1操作、写入工作内存
            inc++;
        }
    }
    
    public static class SynchronizedTest
    {
        public int inc = 0;
        
        public synchronized void increase()
        {
            inc++;
        }
        
        public void test()
        {
            final Test test = new Test();
            for(int i = 0; i < 10; i++)
            {
                new Thread() {
                    public void run()
                    {
                        for(int j = 0; j < 1000; j++)
                            test.increase();
                    };
                }.start();
            }
            
            while (Thread.activeCount() > 1) // 保证前面的线程都执行完
                Thread.yield();
            System.out.println(test.inc);
        }
    }
    
    public class LockTest
    {
        public int inc = 0;
        
        Lock lock = new ReentrantLock();
        
        public void increase()
        {
            lock.lock();
            try
            {
                inc++;
            }
            finally
            {
                lock.unlock();
            }
        }
        
        public void test()
        {
            final Test test = new Test();
            for(int i = 0; i < 10; i++)
            {
                new Thread() {
                    public void run()
                    {
                        for(int j = 0; j < 1000; j++)
                            test.increase();
                    };
                }.start();
            }
            
            while (Thread.activeCount() > 1) // 保证前面的线程都执行完
                Thread.yield();
            System.out.println(test.inc);
        }
    }
    
    public class AtomicIntegerTest
    {
        public AtomicInteger inc = new AtomicInteger();
        
        public void increase()
        {
            inc.getAndIncrement();
        }
        
        public void test()
        {
            final Test test = new Test();
            for(int i = 0; i < 10; i++)
            {
                new Thread() {
                    public void run()
                    {
                        for(int j = 0; j < 1000; j++)
                            test.increase();
                    };
                }.start();
            }
            
            while (Thread.activeCount() > 1) // 保证前面的线程都执行完
                Thread.yield();
            System.out.println(test.inc);
        }
    }
}
