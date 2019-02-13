package com.example.demo.test;

import java.lang.reflect.Field;
import java.math.BigDecimal;

public class StringTest {

    public static void main(String [] a) throws Exception{
//        internTest();
//        stringTest();
//        reflTest();
//        floatTest();
        finalStringTest();
//        strsub();
//        bufferTest();
    }
    public static void bufferTest(){
        StringBuffer sb= new StringBuffer();
        sb.append("123");
        if(sb.toString()==null) System.out.println("null");
        else System.out.println("=="+sb.toString());
        System.out.println(sb.length());
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
        System.out.println("开始------"); //true
        System.out.println(tt==ttt); //true
        String ht = tt+ttt;
        refString(tt);
        System.out.println("ht=="+ht); //ht==hello worldhello world
        System.out.println("tt=="+tt);  //tt==hello world
        System.out.println("ttt=="+ttt); //ttt==hello_world
        System.out.println(tt+"---"+ttt); //hello world---hello_world
        System.out.println(tt==ttt); //true 明明上面打印的不一样,还是true
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
        System.out.println(s2==s); //true
        System.out.println(ss1==ss); //true
    }
    public static void stringTest(){
        String m = "123456";
        String n = new String(m);
        String v = new String("123456");
        System.out.println(m==n); //false
        System.out.println(m==v); //false
        refString(m);
        System.out.println(m+"----"+v); //12345_----12345_
    }

    public static void floatTest(){
        float f1 = 124.1f;
        float f2 = 123.8f;
        float fs = f1-f2;//相减默认是的double类型
        System.out.println(fs); //0.29999542
        BigDecimal bd = new BigDecimal(Float.toString(f1));
        BigDecimal bd2 = new BigDecimal(Float.toString(f2));
        System.out.println(bd.subtract(bd2).floatValue());//0.3
    }
    public static void strsub(){
        String ss = "12345;6789";
        ss = ss.substring(0,ss.indexOf(";")+1);
        System.out.println(ss);
    }
    public static int tetException(){
        try {
            Integer.parseInt("jj");
            return 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 1;
        } finally {
            return 2;
        }

    }
}
