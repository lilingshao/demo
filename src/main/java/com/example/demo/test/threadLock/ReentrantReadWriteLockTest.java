package com.example.demo.test.threadLock;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantReadWriteLock;

@Slf4j
public class ReentrantReadWriteLockTest {
    private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String args[]){
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        new Thread(){
            public void run(){
                test.get(Thread.currentThread());
            }
        }.start();
        new Thread(){
            public void run(){
                test.get(Thread.currentThread());
            }
        }.start();
    }

    public void get(Thread thread){
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start <=1){
                log.info(thread.getName()+"正在进行读");
            }
            log.info(thread.getName()+"读完毕");
        } finally {
            rwl.readLock().unlock();
        }
    }
}
