package com.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

public class ExecutorServiceTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//创建单线程线程池
		ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
		//创建可回收的线程池
		ExecutorService newCacheThreadPool = Executors.newCachedThreadPool();
		
		//获取本机的cpu核心数
		int cpuNum = Runtime.getRuntime().availableProcessors();
		System.out.println(cpuNum);
		
		//创建固定数量线程池
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(cpuNum);
		
		//创建可调度的线程池
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(cpuNum);
		
		
		/*for(int i = 0; i < 50; i++) {
			newCacheThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("thread name: " + Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch(InterruptedException exception) {
						exception.printStackTrace();
					}
				}
			});
		}
		
		Thread.sleep(1000);
		System.out.println("--------------------");
		for(int i = 0; i < 5; i++) {
			newCacheThreadPool.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("thread name: " + Thread.currentThread().getName());
					try {
						Thread.sleep(1000);
					} catch(InterruptedException exception) {
						exception.printStackTrace();
					}
				}
			});
		}
		newCacheThreadPool.shutdown();*/
		
		for(int i = 0; i < 5; i++) {
			Future<String> submit = newFixedThreadPool.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					System.err.println(Thread.currentThread().getName() + "---->正在工作");
					Thread.sleep(5000);
					System.err.println(Thread.currentThread().getName() + "---->工作结束");
					return "b--" + Thread.currentThread().getName();
				}
			});
			//从Future中get结果，这个方法是会被阻塞的，一直要等到线程任务返回结果
			//System.out.println("获取到结果：" + submit.get());
		}
		newFixedThreadPool.shutdown();
	}
}
