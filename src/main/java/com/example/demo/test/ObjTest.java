package com.example.demo.test;

public class ObjTest implements Cloneable{
	public int kk = 0;
	public static void main(String args[]) throws Exception{
		ObjTest t1 = new ObjTest();
		ObjTest t2 = new ObjTest();
		System.out.println(t1==t2);
		System.out.println(t1.equals(t2));
		System.out.println(t1.hashCode());
		System.out.println(t2.hashCode());
		//上面对象比较其实就是比较的 == ,如果需equals,可以重写equals方法
		//下面string有自己的比较equals方法,比较对象和char数组是不是都相同
		String a=new String("foo");
		String b=new String("foo");
		System.out.println("---------------");
		System.out.println(a.equals(b));

	}
}
