package com.example.demo.test.threadLock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
@Slf4j
public class ReentrantLockTest {
    private List<Integer> list = new ArrayList<>();
    Lock lock = new ReentrantLock();    //注意这个地方
    public static void main(String args[]){
        final ReentrantLockTest test = new ReentrantLockTest();
        new Thread(){
            public void run(){
                test.insert(Thread.currentThread());
            }
        }.start();
        new Thread(){
            public void run(){
                test.insert(Thread.currentThread());
            }
        }.start();
        try {
            Thread.sleep(1000 * 1);
            StringBuffer sb = new StringBuffer();
            for(Integer tt: test.list){
                sb.append(tt);
                sb.append(",");
            }
            log.info(sb.toString());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insert(Thread thread) {
        lock.lock();
        try {
            System.out.println(thread.getName()+"得到了锁");
            for(int i=0;i<5;i++) {
                list.add(i);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            System.out.println(thread.getName()+"释放了锁");
            lock.unlock();
        }
    }
}
