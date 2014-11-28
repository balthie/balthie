/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月28日 下午2:49:46
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.lock;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月28日 下午2:49:46
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO 
 */
public class BootStrap
{
    
    public static void main(String[] args)
    {
        Printer printer = new Printer();
        
        Thread[] thread = new Thread[10];
        
        for(int i=0;i<10;i++)
        {
            thread[i] = new Thread(new PrintJob(printer));
        }
        
        for(int i=0;i<10;i++)
        {
            thread[i].start();
        }
    }
}
