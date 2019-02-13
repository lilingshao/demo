package com.example.demo.test.threadLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 参考链接: https://www.cnblogs.com/zouzz/p/6593748.html
 * 公平锁条件队列
 */
public class ConditionLockDemo {
	volatile int key = 0;
	Lock l = new ReentrantLock();
	Condition c = l.newCondition();//Condition实例的内部实际上维护了一个队列

	public static void main(String a[]){
		ConditionLockDemo demo = new ConditionLockDemo();
		new Thread(demo.new A()).start();
		new Thread(demo.new B()).start();
	}

	class A implements Runnable{
		@Override
		public void run() {
			int i=10;
			while (i>0){
				l.lock();
				try {
					if(key==1){
						System.out.println("A is Running...");
						System.out.println("A is Running...");
						i--;
						key = 0;
						c.signal(); //等待条件的队列去排队获取锁--看链接的图,注释掉后 会一直阻塞
					}else{
						c.awaitUninterruptibly();//让线程去 等待条件的队列
					}
				}finally {
					System.out.println("A...");
					l.unlock();
				}
			}
		}
	}
	class B implements Runnable{
		@Override
		public void run() {
			int i = 10;
			while(i > 0){
				l.lock();
				try{
					if(key == 0){
						System.out.println("B is Running");
						i--;
						key = 1;
						c.signal();
					}else{
						c.awaitUninterruptibly();
					}
				}finally{
					l.unlock();
				}
			}
		}
	}

}
