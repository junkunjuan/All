package com.test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueProducer implements Runnable{
	BlockingQueue<String> queue;
	Random random = new Random();
	
	public BlockingQueueProducer(BlockingQueue<String> queue) {
		this.queue = queue;
	}
	
	public void run() {
		for(int i = 0; i < 10; i++) {
			try  {
				Thread.sleep(random.nextInt(10));
				String task = Thread.currentThread().getName() + " made a product " + i;
				System.out.println(task);
				queue.put(task);	//阻塞方法
			} catch(InterruptedException exception) {
				exception.printStackTrace();
			}
		}
	}
}
