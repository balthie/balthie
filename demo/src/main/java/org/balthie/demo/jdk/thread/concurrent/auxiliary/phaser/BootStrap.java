package org.balthie.demo.jdk.thread.concurrent.auxiliary.phaser;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.Phaser;

public class BootStrap
{
    public static void main(String[] args)
    {
        System.out.println(MessageFormat.format(" search task being at【{0, time, HH:mm:ss:ms}】",
                new Date()));
        // 指定参与同步的线程数量 
        Phaser phaser = new Phaser(3);
        
        FileSearch program = new FileSearch("d:\\Program Files", "log", phaser);
        FileSearch programX86 = new FileSearch("d:\\Program Files (x86)", "log", phaser);
        FileSearch sonar = new FileSearch("d:\\Sonar", "log", phaser);
        
        Thread t1 = new Thread(program);
        Thread t2 = new Thread(programX86);
        Thread t3 = new Thread(sonar);
        
        t1.start();
        t2.start();
        t3.start();
        
        try
        {
            //主线程等待子线程的终止
            t1.join();
            t2.join();
            t3.join();
        }
        catch (InterruptedException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(MessageFormat.format(" search task end at【{0, time, HH:mm:ss:ms}】",
                new Date()));
    }
}
