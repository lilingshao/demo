package com.example.demo.test;

public class ThreadCount {
    private static int n1=0;
    private static int n2=0;
    private static int n3=0;
    private static int sum=0;
    private static volatile boolean flag=true;
    public static void main(String a[]){
        method02();
        System.out.println("end....");
    }
    /**
     * 第二种实现方式，线程执行顺序可以在方法中调换
     */
    private static void method02(){
        Runnable runnable1 = new Runnable() {
            @Override public void run() {
                while (flag){
                    n1++;
//                    System.out.println("==="+n1);
                }
            }
        };
        Runnable runnable2 = new Runnable() {
            @Override public void run() {
                while (flag){
                    n2++;
//                    System.out.println("======"+n2);
                }
            }
        };
        Runnable runnable3 = new Runnable() {
            @Override public void run() {
                while (flag){
                    n3++;
//                    System.out.println("========="+n3);
                }
            }
        };
        Runnable runnableSum = new Runnable() {
            @Override public void run() {
                sum = n1+n2+n3;
                System.out.println(sum);
            }
        };
        Thread t1 = new Thread(runnable1, "t1");
        Thread t2 = new Thread(runnable2, "t2");
        Thread t3 = new Thread(runnable3, "t3");
        Thread tsum = new Thread(runnableSum, "t4");
        try {
            t1.start();
            t2.start();
            t3.start();
            Thread.sleep(1000);
            //变量用 volatile 修饰,否则出问题
            ThreadCount.flag=false;
            t1.join();
            t2.join();
            t3.join();
            tsum.start();
            System.out.println((n1+n2+n3)+"---"+n1+"--"+n2+"--"+n3);
//            Thread.sleep(50);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
