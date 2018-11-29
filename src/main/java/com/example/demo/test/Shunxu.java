package com.example.demo.test;

public class Shunxu {
    static {
        System.out.println("111");
    }
    Shunxu(){
        System.out.println("222");
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
