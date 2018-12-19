package com.example.demo.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueneTest {
	/**
	 * LinkedBlockingQueue 多用于任务队列
	 * ConcurrentLinkedQueue  多用于消息队列
	 * 多个生产者，对于LBQ性能还算可以接受；但是多个消费者就不行了mainLoop需要一个timeout的机制，否则空转，cpu会飙升的。LBQ正好提供了timeout的接口，更方便使用
	 * 如果CLQ，那么我需要收到处理sleep
	 * 单消费者   用 LinkedBlockingqueue
	 * 多消费者   用 ConcurrentLinkedQueue
	 * @param a
	 */
	public static void main(String a[]){
		QueneTest test = new QueneTest();
//		test.ArrayBlockingQueue();//基于数组的阻塞队列--有界
//		test.LinkBlockingQuene();//基于链表的阻塞队列--可有界无界
		test.ConcurrentLinkedQueue();//基于链接节点的无界线程安全队列--非阻塞队列
	}

	/**
	 * ConcurrentLinkedQueue是Queue的一个安全实现．
	 * Queue中元素按FIFO原则进行排序．内部采用CAS保证并发安全
	 */
	public void ConcurrentLinkedQueue(){
		ConcurrentLinkedQueue bq = new ConcurrentLinkedQueue();
		bq.offer("1");
		bq.offer("2");
		bq.offer("3");
		bq.add("1");
		while (bq.size()>0){
			System.out.println(bq.poll());
		}
	}
	//LinkedBlockingQueue实现是线程安全的，实现了先进先出等特性
	public void LinkBlockingQuene(){
		BlockingQueue<String> bq = new LinkedBlockingQueue<>(3);
		bq.offer("1");
		bq.offer("1");
		bq.offer("1");
//		bq.add("1"); //如果队列满则 抛出: java.lang.IllegalStateException: Queue full
		while (bq.size()>0){
			System.out.println(bq.poll()); //task方法: 元素没有会一直等待，有就返回
		}
	}
	public void ArrayBlockingQueue(){
		BlockingQueue<String> basket = new ArrayBlockingQueue<String>(3);
		try {
			basket.put("An apple");
			basket.put("An apple2");
			basket.put("An apple3"); //队列满的话put方法会阻塞
			basket.offer("An apple4");//offer方法队列满不会阻塞

			while (basket.size()>0){
				System.out.println(basket.take()); //task方法: 元素没有会一直等待，有就返回
				System.out.println(basket.poll()); //poll方法: 有就返回没有就返回null
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
