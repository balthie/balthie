package org.balthie.demo.jdk.thread.concurrent.auxiliary.semaphore;

import java.text.MessageFormat;
import java.util.Date;

public class PrintJob implements Runnable
{
    private Printer printer;
    
    public PrintJob(Printer printer)
    {
        super(); 
        this.printer = printer;
        System.out.println(MessageFormat.format("【{0}】printJob create at【{1, time, HH:mm:ss:ms}】", Thread.currentThread()
                .getName(), new Date()));
    }
    
    @Override
    public void run()
    {
        printer.print(new Object());
    }
}
