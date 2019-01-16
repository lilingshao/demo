package com.example.demo.test;

/**
 * 递归
 */
public class DiguiTest {
	public static void main(String args[]){
		System.out.println(getInt(1));
	}
	public static int getInt(int m){
		if(m<9){
			m++;
			System.out.println("m=="+m);
			return getInt(m);
		}else {
			return m;
		}
	}
}
