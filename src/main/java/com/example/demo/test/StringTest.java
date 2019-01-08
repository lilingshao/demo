package com.example.demo.test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class StringTest {

    public static void main(String [] a) throws Exception{
//        internTest();
//        stringTest();
//        reflTest();
//        floatTest();
//        finalStringTest();
        strsub();
    }
    public static void reflTest() throws Exception{
        String xx=new String("hello world");
        String xxx=new String("hello world");
        String hello="hello world";
        String yy="hello world";
        String zz = "hello ";
        String pp = zz+"world";
        String p = new String("hello ");
        //通过反射修改hello的value值
        refString(hello);
        refString(zz);
        System.out.println("hello=="+hello);
        System.out.println("xx=="+xx);//
        System.out.println("xxx=="+xxx);
        System.out.println("yy=="+yy);
        //上面四个最终都指向常量池 --xx/xxx先指向堆,再指向常量池 yy/hello直接指向常量池
        System.out.println(hello==pp);
        System.out.println("zz=="+zz);
        System.out.println("pp=="+pp); //打印不变,+在堆里面运算了,而不是常量池
        System.out.println("p=="+p);
    }
    public static void finalStringTest(){
        final String tt = "hello world";
        String ttt = "hello world";
        System.out.println(tt==ttt);
        String ht = tt+ttt;
        refString(ttt);
        System.out.println("ht=="+ht);
        System.out.println("tt=="+tt);
        System.out.println("ttt=="+ttt);
        System.out.println(tt+ttt);
    }
    public static void refString(String str){
        //通过反射修改hello的value值
        try {
            Field hello_field=String.class.getDeclaredField("value");
            System.out.println(hello_field.getName()+"--"+hello_field.toString()+"--"+hello_field.getType());
            hello_field.setAccessible(true);
            char[] value=(char[])hello_field.get(str);
            value[5]='_';
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    public static void internTest(){
        String s = "a123,123";
        String s2 = "a123"+",123";
        String ss = new String("a123").intern();
        String ss1 = "a123";
        System.out.println(s2==s);
        System.out.println(ss1==ss);
    }
    public static void stringTest(){
        String m = "12";
        String n = new String(m);
        String v = new String("12");
        System.out.println(m==n);
        System.out.println(m==v);
    }

    public static void floatTest(){
        float f1 = 124.1f;
        float f2 = 123.8f;
        float fs = f1-f2;//相减默认是的double类型
        System.out.println(fs);
        BigDecimal bd = new BigDecimal(Float.toString(f1));
        BigDecimal bd2 = new BigDecimal(Float.toString(f2));
        System.out.println(bd.subtract(bd2).floatValue());
    }
    public static void strsub(){
        String ss = "12345;6789";
        ss = ss.substring(0,ss.indexOf(";")+1);
        System.out.println(ss);
    }

}
