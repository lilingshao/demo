package com.example.demo.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadFutureTask {
    public static void main(String[] args) {
        Callable<String> callable=new CallableThread();
        FutureTask<String> futureTask=new FutureTask<String>(callable);
        Thread thread=new Thread(futureTask);
        thread.start();//开启一个线程方法
        //以下的方法可与上边的线程并发执行
        doSomething();
        try {
            System.out.println(futureTask.get());//获取线程返回值
        } catch (InterruptedException | ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    private static void doSomething() {
        // TODO Auto-generated method stub
        System.out.println("我是中间的");
    }
}
class CallableThread implements Callable<String>{

    @Override
    public String call() throws Exception {
        // TODO Auto-generated method stub
        doSomeThing();
        return "需要返回的值";
    }

    private void doSomeThing() {
        // TODO Auto-generated method stub
        for(int i=0;i<20;i++)
        System.out.println("我是线程中的方法");
    }

}