package com.example.demo.test.threadLock;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，
 * 如果获取失败（即锁已被其他线程获取），则返回false，也就说这个方法无论如何都会---立即返回
 * tryLock(long time, TimeUnit unit)方法和tryLock()方法是类似的，只不过区别在于这个方法在拿不到锁时---会等待一定的时间，
 * 在时间期限之内如果还拿不到锁，就返回false。如果如果一开始拿到锁或者在等待期间内拿到了锁，则返回true。
 */
@Slf4j
public class TryLockTest {
    private List<Integer> list = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    public static void main(String args[]){
        TryLockTest test = new TryLockTest();
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
    public void insert(Thread thread){
        try {
            if(lock.tryLock(1,TimeUnit.SECONDS)){
    //        if(lock.tryLock()){
                try {
                    log.info(thread.getName()+"得到了锁");
                    for(int i=0;i<5;i++){
                        list.add(i);
                    }
                } finally {
                    log.info(thread.getName()+"释放了锁");
                    lock.unlock();
                }
            }else{
                log.info(thread.getName()+"获取锁失败");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
