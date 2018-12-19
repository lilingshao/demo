package com.example.demo.test;

public class TestFloat {
	public static void main(String a[]){
		test();
		String ss = "1234566";
		char[] sc = ss.toCharArray();
	}
	public static void test(){
		double f1 = 1.0;
		int f2 = 1;
		double f = f2+f1;
		System.out.println(f);
		float ffff = f2;
		System.out.println(ffff); //0.30000000000000004
		System.out.println(3*0.1); //0.30000000000000004

		float x;
		x = 10/4;
		System.out.println("10/4=="+x);
		x = (float) (10/4.0);
		System.out.println("10/4.0=="+x);
		x = (float) (10.0/4);
		System.out.println("10.0/4=="+x);
		x = (float) (10.0/4.0);
		System.out.println("10.0/4.0=="+x);
	}
}
