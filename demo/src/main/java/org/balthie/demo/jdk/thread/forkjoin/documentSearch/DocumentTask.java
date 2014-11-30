package org.balthie.demo.jdk.thread.forkjoin.documentSearch;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveTask;

public class DocumentTask extends RecursiveTask<Integer>
{
    private String document[][];
    private int start,end;
    private String word;
    
    public DocumentTask(String[][] document, int start, int end, String word)
    {
        super();
        this.document = document;
        this.start = start;
        this.end = end;
        this.word = word;
    }



    @Override
    protected Integer compute()
    {
        int result = 0;
        if(end - start <10)
        {
            result = processLines(document, start, end, word);
        }
        else
        {
            int mid = (start + end)/2;
            DocumentTask task1 = new DocumentTask(document, start, mid, word);
            DocumentTask task2 = new DocumentTask(document, mid, end, word);
            invokeAll(task1,task2);
            try
            {
                result = groupResults(task1.get(), task2.get());
            }
            catch (InterruptedException | ExecutionException e)
            {
                e.printStackTrace();
            }
        }
        return result;
    }



    private int groupResults(Integer integer, Integer integer2)
    {
        // TODO Auto-generated method stub
        return 0;
    }



    private int processLines(String[][] document2, int start2, int end2, String word2)
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
