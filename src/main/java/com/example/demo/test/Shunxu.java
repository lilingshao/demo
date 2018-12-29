package com.example.demo.test;

import java.util.Date;

public class Shunxu extends Date {
    static {
        System.out.println("111");
    }
    Shunxu(){
        System.out.println("222");
        System.out.println(super.getClass().getName());
    }
    public static void main(String [] mm){
       new B();
    }
}
class A{
    static {
        System.out.println("B...A");
    }
    A(){
            System.out.println("A...");
        }
}
class B extends A{
    Shunxu t = new Shunxu();
    B(){
        System.out.println("B...");
    }
}
