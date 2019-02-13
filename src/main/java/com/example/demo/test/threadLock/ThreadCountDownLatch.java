package com.example.demo.test.threadLock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * 统计线程CountDownLatch
 */
public class ThreadCountDownLatch {

	public static void main(String a[]) throws InterruptedException{
		//CountDownLatch
//		testCountDownLatch();
		//CyclicBarrier
//		cyclicBarrier();
		cyclicBarrier_now();
	}

	public static void cyclicBarrier_now() throws InterruptedException{
		int N = 4;
		//如果说想在所有线程写入操作完之后，进行额外的其他操作可以为CyclicBarrier提供Runnable参数：
		CyclicBarrier barrier = new CyclicBarrier(4, new Runnable() {
			@Override
			public void run() {
				System.out.println("当前线程"+Thread.currentThread().getName());
			}
		});
		for(int i=0;i<N;i++){
			new Writer(barrier).start();
		}
		Thread.sleep(25000);
		System.out.println("CyclicBarrier重用");
		for(int i=0;i<N;i++){
			new Writer(barrier).start();
		}
	}
	public static void cyclicBarrier() throws InterruptedException{
		int N = 4;
		CyclicBarrier barrier = new CyclicBarrier(4);
		for(int i=0;i<N;i++){
			new Writer(barrier).start();
		}
		Thread.sleep(25000);
		System.out.println("CyclicBarrier重用");
		for(int i=0;i<N;i++){
			new Writer(barrier).start();
		}
	}

	public static void testCountDownLatch() throws InterruptedException{
		//等待线程执行完后才执行下面的方法
		final CountDownLatch latch = new CountDownLatch(2);
		countDownLatch(latch);
		latch.await();
		System.out.println("子线程执行完毕我才执行");
	}
	public static void countDownLatch(CountDownLatch latch){
		new Thread(){
			public void run(){
				try {
					System.out.println("子线程"+Thread.currentThread().getName()+"正在执行");
					Thread.sleep(3000);
					System.out.println("子线程"+Thread.currentThread().getName()+"执行完毕");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		new Thread(){
			public void run(){
				try {
					System.out.println("子线程2"+Thread.currentThread().getName()+"正在执行");
					Thread.sleep(3000);
					System.out.println("子线程2"+Thread.currentThread().getName()+"执行完毕");
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}

	static class Writer extends Thread{
		private CyclicBarrier cyclicBarrier;
		public Writer(CyclicBarrier cyclicBarrier){
			this.cyclicBarrier=cyclicBarrier;
		}
		@Override
		public void run(){
			System.out.println("线程"+Thread.currentThread().getName()+"正在写入数据...");
			try {
				Thread.sleep(5000);      //以睡眠来模拟写入数据操作
				System.out.println("线程"+Thread.currentThread().getName()+"写入数据完毕，等待其他线程写入完毕");

				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}catch(BrokenBarrierException e){
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"所有线程写入完毕，继续处理其他任务...");
		}
	}

}
