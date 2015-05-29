package org.balthie.demo.jdk.thread.forkjoin.documentSearch;

import java.text.MessageFormat;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;

public class BootStrap
{
    private static final String WORD_4_SEARCH = "search";
    
    public static void main(String[] args)
    {
        
        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100, 1000, WORD_4_SEARCH);
         
        RecursiveTask<Integer> task = new DocumentTask(document, 0, 100, WORD_4_SEARCH);
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(task);
         
        do{     
            try
            {
                TimeUnit.SECONDS.sleep(1);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            
        }while(!task.isDone());
         
        // 顺序关闭启动线程，启动一次顺序关闭，执行以前提交的任务，但不接受新任务。如果已经关闭，则调用没有其他作用
        pool.shutdown();
 
        try
        {
            // 等待子线程结束，再继续执行下面的代码
            pool.awaitTermination(1, TimeUnit.HOURS);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
         
        try
        {
            System.out.println(MessageFormat.format("Main : the word 【{0}】 appears 【{1}】times in the document", WORD_4_SEARCH, task.get()));
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }
}
