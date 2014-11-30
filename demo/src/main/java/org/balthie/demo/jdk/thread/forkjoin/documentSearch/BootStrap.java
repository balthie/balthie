package org.balthie.demo.jdk.thread.forkjoin.documentSearch;

import java.util.concurrent.ForkJoinPool;

public class BootStrap
{
    public static void main(String[] args)
    {
        DocumentMock mock = new DocumentMock();
        String[][] document = mock.generateDocument(100, 1000, "the");
         
        ForkJoinPool pool = new ForkJoinPool();
        pool.execute(new DocumentTask(document, 0, 100, "the"));
    }
}
