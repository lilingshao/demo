package com.example.demo.test;


public class ThreadRunPS extends Thread {
    @Override
    public void run() {
//        try {
//            for (int i = 0; i < 50000000; i++) {
//                if (this.interrupted()) {
//                    System.out.println("已经是停止状态了，我要退出了");
//                    throw new InterruptedException();
//                }
//                System.out.println(i);
//            }
//            System.out.println("for结束");
//        } catch (InterruptedException e) {
//            System.out.println("进入run中的catch了");
//            e.printStackTrace();
//        }

    }

    public static void main(String[] args) {
//        try {
//            ThreadRunPS myThread = new ThreadRunPS();
//            myThread.start();
//            Thread.sleep(1000);
//            myThread.interrupt();
//            System.out.println("end");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
//Java多线程程序休眠、暂停与停止
class MythreadY extends Thread{
    @Override
    public void run() {
        long beginTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0; i < 500; i++) {
            /**
             * 可以使用yield方法进行暂停。
             * yield()方法的作用是放弃当前的CPU资源，将它让给其他任务去占用CPU执行时间。但放弃的时间不确定，有可能刚刚放弃，马上又获得CPU时间片。
             * 不去掉 用时: 21ms
             * 去掉注释//Thread.yield()后。
             * 用时: 12004ms
             */
//            Thread.yield();
            count = count + (i + 1);
            System.out.println(i);
            if(i==10){
//                Thread.currentThread().stop();
                Thread.currentThread().interrupt();
                System.out.println("是否停止1？ " + Thread.interrupted());
                System.out.println("是否停止2？ " + Thread.interrupted());
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("用时: " + (endTime - beginTime) + "ms");
    }

    public static void main(String[] args) {
        MythreadY myThread = new MythreadY();
        myThread.start();
    }
}
