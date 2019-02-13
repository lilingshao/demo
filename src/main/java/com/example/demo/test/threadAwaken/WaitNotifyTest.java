package com.example.demo.test.threadAwaken;

import lombok.extern.slf4j.Slf4j;

/**
 * 使用wait，notify来实现等待唤醒功能至少有两个缺点：
 * 1.由例子可知,wait和notify都是Object中的方法,在调用这两个方法前必须先获得锁对象，这限制了其使用场合:只能在同步代码块中。
 * 2.另一个缺点可能上面的例子不太明显，当对象的等待队列中有多个线程时，notify只能随机选择一个线程唤醒，无法唤醒指定的线程。
 */
@Slf4j
public class WaitNotifyTest {
    private static Object obj = new Object();
    public static void main(String args[]){
        new Thread(new WaitThread()).start();
        new Thread(new NotifyThread()).start();

    }
    static class WaitThread implements Runnable{
        @Override
        public void run() {
            synchronized (obj){
                log.info("start wait");
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("end wait");
            }
        }
    }
    static class NotifyThread implements Runnable{
        @Override
        public void run() {
            synchronized (obj){
                log.info("start notify");
                obj.notify();
                log.info("end notify");
            }
        }
    }
}
