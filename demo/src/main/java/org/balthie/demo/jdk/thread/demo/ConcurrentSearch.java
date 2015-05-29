package org.balthie.demo.jdk.thread.demo;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

// 比较各种线程执行方式
// http://www.importnew.com/14506.html
public class ConcurrentSearch
{
    public static void main(String[] args)
    {
        
    }
    
    // 方法3：通过并行流，使用ForkJoinPool (FJP)
    private static String getFirstResultByForkJoinPool(String question, List<String> engines)
    {
        // get element as soon as it is available
        Optional<String> result = engines.stream().parallel().map((base) -> {
            String url = base + question;
            return WS.url(url).get();
        }).findAny();
        return result.get();
    }
    
    private static String getFirstResultExecutors(String question, List<String> engines)
    {
        ExecutorCompletionService<String> service = new ExecutorCompletionService<String>(Executors.newFixedThreadPool(4));
        
        for(String base : engines)
        {
            String url = base + question;
            service.submit(() -> {
                return WS.url(url).get();
            });
        }
        try
        {
            return service.take().get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            return null;
        }
    }
    
    private static String getFirstResult(String question, List<String> engines)
    {
        AtomicReference<String> result = new AtomicReference<>();
        for(String base : engines)
        {
            String url = base + question;
            new Thread(() -> {
                result.compareAndSet(null, WS.url(url).get());
            }).start();
        }
        while (result.get() == null)
            ; // wait for some result to appear
        return result.get();
    }
}

class WS
{
    public static AtomicReference<String> url(String url)
    {
        // 模拟一个耗时的搜索引擎，搜索逻辑
        try
        {
            TimeUnit.SECONDS.sleep(2);
        }
        catch (InterruptedException e)
        {
        }
        AtomicReference<String> r = new AtomicReference<>("result");
        return r;
    }
    
}
