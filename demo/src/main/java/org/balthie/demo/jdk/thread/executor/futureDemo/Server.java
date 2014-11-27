package org.balthie.demo.jdk.thread.executor.futureDemo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
	private ThreadPoolExecutor executor;
	
	List<Future<Integer>> resultList = new ArrayList<Future<Integer>>();
	
	
	    
	public static void main(String[] args) {
		Random random = new Random();
		Server server = new Server();
		
		System.out.println(MessageFormat.format(
				"【{0}】server begin at 【{1,time, HH:mm:ss:ms}】", Thread
						.currentThread().getName(), new Date()));
		for(int i=1;i<21;i++)
		{
			try {
				Integer r = random.nextInt(20);
				Task t = new Task(r, String.valueOf(i));
				Future<Integer> result = server.executor.submit(t);
				
				server.resultList.add(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		server.printCompletedResult();
		System.out.println(MessageFormat.format(
				"【{0}】server end at 【{1,time, HH:mm:ss:ms}】", Thread
						.currentThread().getName(), new Date()));
		
	}

	public Server() {
		super();
		this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
	}

	public void printCompletedResult()
	{
		System.out.println(" printCompletedResult begin at " + new Date());
		
		int resultSize = 0;
		do
		{
			resultSize =  this.resultList.size();
			System.out.println(MessageFormat.format(
					" total Task count 【{0}】", resultSize));
			System.out.println(MessageFormat.format(
					" completed Task count 【{0}】", this.executor.getCompletedTaskCount()));
			
			if(this.executor.getCompletedTaskCount() > 10)
			{
				System.out.println(MessageFormat.format(
						" all Task done at 【{0,time, HH:mm:ss:ms}】", new Date()));
				
				for(Future<Integer> f : resultList)
				{
					try {
						System.out.println(MessageFormat.format(
								" result 【{0}】", f.get()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
				
				this.executor.shutdown();
			}
			else
			{
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}while(this.executor.getCompletedTaskCount() < resultSize);
			
	}
}
