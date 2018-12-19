package com.example.demo.test;

import java.util.*;

/**
 * 非静态方法synchronized方法,多个对象可以同时访问
 * 静态方法 每次互斥,同时只能一个访问
 */
public class Synchr {
    static Map<String,String> map = new LinkedHashMap<>();

    public static void main(String [] a){
        tent2();
    }
    public static void addMap(){
        map.put("li","li");
        map.put("li2","li");
    }
    public static synchronized void todo(){
        System.out.println("11111111111111");
        try {
            Thread.sleep(1000*5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Iterator<Map.Entry<String,String>> it = map.entrySet().iterator();
        while (it.hasNext()){
            Map.Entry<String,String> m = it.next();
            System.out.println(m.getKey()+"--"+m.getValue());
        }

        System.out.println("111111111---end");
    }

    //不能同时访问
    public static void tent2(){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                Synchr.todo();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                new Synchr().addMap();
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
