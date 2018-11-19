package com.example.demo.test;

/**
 * 非静态方法synchronized方法,多个对象可以同时访问
 * 静态方法 每次互斥,同时只能一个访问
 */
public class Synchr {
    public static synchronized void todo(){
        System.out.println("11111111111111");
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("111111111---end");
    }
    public static synchronized void todo2(){
        System.out.println("22222222");
    }
    public static void main(String [] a){
        tent1();
//        tent2();
    }
    //不能同时访问
    public static void tent2(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                new Synchr().todo();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                new Synchr().todo();
            }
        });
        t.start();
        try {
            Thread.sleep(1000*1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
    //不能同时访问
    public static void tent1(){
        Synchr tttt = new Synchr();
        Synchr tt = new Synchr();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                tt.todo();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                tttt.todo2();
            }
        });
        t.start();
        try {
            Thread.sleep(1000*1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
