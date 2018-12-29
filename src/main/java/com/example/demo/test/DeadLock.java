package com.example.demo.test;

/**
 * 死锁
 */
public class DeadLock {
	static String obj1 = "1111";
	static String obj2 = "2222";

	public static void main(String[] aa){
		DeadLock dd = new DeadLock();
		Thread t1 = new Thread(dd.new Lock1());
		Thread t2 = new Thread(dd.new Lock2());

		t1.start();
		t2.start();
	}

	class Lock1 implements Runnable{
		@Override
		public void run() {
			System.out.println("lock1 running...");
			try {
				synchronized (DeadLock.obj1){
					Thread.sleep(1000*3);
					synchronized(DeadLock.obj2){
						System.out.println("Lock1 lock obj2");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	class Lock2 implements Runnable{

		@Override
		public void run() {
			System.out.println("lock2 running...");
			try {
				synchronized (DeadLock.obj2){
					Thread.sleep(1000*3);
					synchronized(DeadLock.obj1){
						System.out.println("Lock2 lock obj1");
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
