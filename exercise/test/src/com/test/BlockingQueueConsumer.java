package com.test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueConsumer implements Runnable{
	BlockingQueue<String> queue;
	Random random = new Random();
	
	public BlockingQueueConsumer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	public void run() {
		try {
			Thread.sleep(random.nextInt(10));
			System.out.println(Thread.currentThread().getName() + " trying ...");
			String tmp = queue.take();	//如果队列为空，会阻塞当前线程
			System.out.println(Thread.currentThread().getName() + " get a job" + tmp);
		} catch(InterruptedException exception) {
			exception.printStackTrace();
		}
	}
	
}
