package com.test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		BlockingQueue<String> queue = new LinkedBlockingQueue<>(2);
		BlockingQueueConsumer consumer = new BlockingQueueConsumer(queue);
		BlockingQueueProducer producer = new BlockingQueueProducer(queue);
		
		for(int i = 0; i < 3; i++) {
			new Thread(producer, "Producer" + (i + 1)).start();
		}
		for(int i = 0; i < 5; i++) {
			new Thread(consumer, "Consumer" + (i + 1)).start();
		}
	}
}
