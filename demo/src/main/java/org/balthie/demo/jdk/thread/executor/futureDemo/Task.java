package org.balthie.demo.jdk.thread.executor.futureDemo;

import java.text.MessageFormat;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Integer> {
	private Integer number;

	private String name;

	@Override
	public Integer call() throws Exception {
		System.out.println(MessageFormat.format(
				"【{0}】Task【{1}】: begin at 【{2,time, HH:mm:ss:ms}】", Thread
						.currentThread().getName(), name, new Date()));
		// 计算阶乘
		int result = 1;
		if ((number == 0) || (number == 1)) {
			result = 1;
		} else {
			for (int i = 2; i <= number; i++) {
				result *= i;
				TimeUnit.MILLISECONDS.sleep(50);
			}
		}
    
		System.out.println(MessageFormat.format(
				"【{0}】Task【{1}】: end on 【{2,time, HH:mm:ss:ms}】", Thread
						.currentThread().getName(), name, new Date()));
		return result;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Task(Integer number, String name) {
		super();
		this.number = number;
		this.name = name;
	}
}
