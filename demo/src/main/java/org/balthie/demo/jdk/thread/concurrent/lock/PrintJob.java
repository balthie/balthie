/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月28日 下午2:29:28
 * @description TODO 一句话描述
 */
package org.balthie.demo.jdk.thread.concurrent.lock;

import java.text.MessageFormat;
import java.util.Date;

/**
 * @author：balthie + 10050
 * @createtime ： 2014年11月28日 下午2:29:28
 * @description TODO 一句话描述
 * @since version 初始于版本 TODO
 */
public class PrintJob implements Runnable
{
    private Printer printer;
    
    public PrintJob(Printer printer)
    {
        super();
        this.printer = printer;
        System.out.println(MessageFormat.format("【{0}】printJob create at【{1, time, HH:mm:ss:ms}】", Thread
                .currentThread().getName(), new Date()));
    }

    @Override
    public void run()
    {
        printer.print(new Object());
    }
    
}
