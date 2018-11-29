package com.example.demo.test;

import java.lang.reflect.Field;

public class StringTest {
    public static void main(String [] a) throws Exception{
//        String s = "a123,123";
//        String s2 = "a123"+",123";
//        String ss = new String("a123").intern();
//        String ss1 = "a123";
//        System.out.println(s2==s);
//        System.out.println(ss1==ss);
//
//        String m = "12";
//        String n = new String(m);
//        String v = new String("12");
//        System.out.println(m==n);
//        System.out.println(m==v);

        String xx=new String("hello world");
        String xxx=new String("hello world");
        String hello="hello world";
        String yy="hello world";
        String zz = "hello ";
        String pp = zz+"world";
        String p = new String("hello ");
        //通过反射修改hello的value值
        Field hello_field=String.class.getDeclaredField("value");
        System.out.println(hello_field.getName()+"--"+hello_field.toString()+"--"+hello_field.getType());
        hello_field.setAccessible(true);
        char[] value=(char[])hello_field.get(hello);
        value[5]='_';
        char[] value2=(char[])hello_field.get(zz);
        value2[5]='_';
        System.out.println(hello);
        System.out.println(xx);
        System.out.println(xxx);
        System.out.println(yy);
        System.out.println(hello==pp);
        System.out.println(zz);
        System.out.println(pp);
        System.out.println(p);

    }

}
