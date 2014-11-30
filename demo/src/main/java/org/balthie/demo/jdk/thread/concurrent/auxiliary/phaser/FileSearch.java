package org.balthie.demo.jdk.thread.concurrent.auxiliary.phaser;

import java.io.File;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class FileSearch implements Runnable
{
    private String initPath;
    
    private String end;
    
    private Phaser phaser;
    
    private List<String> results = new ArrayList<String>();
    
    public FileSearch(String initPath, String end, Phaser phaser)
    {
        super();
        this.initPath = initPath;
        this.end = end;
        this.phaser = phaser;
    }
    
    private void directoryProcess(File file)
    {
        File[] list = file.listFiles();
        if(list != null && list.length > 0)
        {
            for(File f : list)
            {
                if(f.isDirectory())
                {
                    directoryProcess(f);
                }
                else
                {
                    fileProcess(f);
                }
            }
        }
    }
    
    private void fileProcess(File f)
    {
        if(f!=null && f.getName().endsWith(end))
        {
            results.add(f.getAbsolutePath());
        }
    }
    
    private void filterResults()
    {
        List<String> newResults = new ArrayList<String>();
        long actualDate = System.currentTimeMillis();
        
        for(String path : results)
        {
            File f = new File(path);
            long modifiedTime = f.lastModified();
            if(actualDate - modifiedTime < TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS))
            {
                newResults.add(path);
            }
        }
        
        results = newResults;
    }
    
    private boolean checkResults()
    {
        if(results == null || results.isEmpty())
        {
            System.out.println(MessageFormat.format("【{0}】Phaser【{1}】 get 0 result, end at【{2, time, HH:mm:ss:ms}】",
                    Thread.currentThread().getName(), phaser.getPhase(),new Date()));
            //到达集合点，不参加下一个阶段
            phaser.arriveAndDeregister();
            return false;
        }
        else
        {
            System.out.println(MessageFormat.format("【{0}】Phaser【{1}】 get 【{2}】 result, end at【{3, time, HH:mm:ss:ms}】",
                    Thread.currentThread().getName(), phaser.getPhase(), results.size(),new Date()));
            // 到达集合点，阻塞等待参加下一个阶段
            phaser.arriveAndAwaitAdvance();
            return true;
        }
    }
    
    private void showInfo()
    {
        System.out.println(MessageFormat.format("【{0}】 show info ",
                Thread.currentThread().getName()));
        for(String path : results)
        {
            System.out.println(MessageFormat.format("【{0}】 find file 【{1}】",
                    Thread.currentThread().getName(), path));
        }
        phaser.arriveAndAwaitAdvance();
    }
    
    @Override
    public void run()
    {
        phaser.arriveAndAwaitAdvance();
        System.out.println(MessageFormat.format("【{0}】Phaser【{1}】 begin at【{2, time, HH:mm:ss:ms}】",
                Thread.currentThread().getName(), phaser.getPhase(),new Date()));
        
        File f = new File(initPath);
        if(f.isDirectory())
        {
            this.directoryProcess(f);
        }
        
        if(!checkResults())
        {
            return;
        }
        
        showInfo();
        filterResults();
        
        if(!checkResults())
        {
            return;
        }
        
        showInfo();
        
        phaser.arriveAndAwaitAdvance();
        System.out.println(MessageFormat.format("【{0}】Phaser【{1}】 end at【{2, time, HH:mm:ss:ms}】",
                Thread.currentThread().getName(), phaser.getPhase(),new Date()));
    }
    
    public String getInitPath()
    {
        return initPath;
    }
    
    public void setInitPath(String initPath)
    {
        this.initPath = initPath;
    }
    
    public String getEnd()
    {
        return end;
    }
    
    public void setEnd(String end)
    {
        this.end = end;
    }
    
    public Phaser getPhaser()
    {
        return phaser;
    }
    
    public void setPhaser(Phaser phaser)
    {
        this.phaser = phaser;
    }
    
    public List<String> getResults()
    {
        return results;
    }
    
    public void setResults(List<String> results)
    {
        this.results = results;
    }
    
}
