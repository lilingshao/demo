package com.example.demo.test.threadAwaken;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

@Slf4j
public class LockSupportTest {

    public static void main(String a[]){
        Thread thread = new Thread(new ParkThread());
        thread.start();
        log.info("开始唤醒线程");
        LockSupport.unpark(thread);
        log.info("结束唤醒线程");
    }

    static class ParkThread implements Runnable{
        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("开始线程阻塞");
            LockSupport.park();
            log.info("结束线程阻塞");
        }
    }
}
