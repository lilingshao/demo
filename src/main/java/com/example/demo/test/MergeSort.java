package com.example.demo.test;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

	public static int mk = 0;

	public static void main(String aras[]){
//		int[] arr = {1,9,3,12,7,8,3,4,65,22};
		int[] arr = {9,3};
		System.out.println("length=="+arr.length);
		int[] temp = new int[arr.length];
		mergeSort(arr, 0, arr.length-1, temp,0);
		System.out.println("result=="+Arrays.toString(arr));
	}

	/**
	 * 二路归并  使用递归解决.
	 * 通过先递归的分解数列，再合并数列就完成了归并排序
	 * @param a
	 * @param first 数组的起始下标
	 * @param last 数组的结束下标
	 * @param temp 辅助数组
	 */
	private static void mergeSort(int a[],int first,int last,int temp[],int mm){
		System.out.println("f=="+first+", lt=="+last+",mm=="+mm);
		if(first<last){
			int mid=(first+last)/2;
			mergeSort(a,first,mid,temp,1);//左边去递归,排序和右边没关系
			mergeSort(a,mid+1,last,temp,2);//右半部分递归排序
			mergeArray(a,first,mid,last,temp); //有序序列合并.
		}
	}

	/**
	 * 将a[first, mid] 和 a[mid+1, last] 合并
	 * @param a
	 * @param first
	 * @param mid
	 * @param last
	 * @param temp
	 */
	private static void mergeArray(int a[],int first,int mid,int last,int temp[]){
		int i=first,j=mid+1;	//设置两个数组的起始边界
		int m=mid,n=last;	//设置两个数组的结束边界

		int k=0;

		while (i<=m && j<=n){
			if(a[i]<a[j]){
				temp[k++] = a[i++];
			}else{
				temp[k++] = a[j++];
			}
		}
		while (i<=m) temp[k++] = a[i++]; //后面多出的数据添加

		while (j<=n) temp[k++] = a[j++];

		for(i=0;i<k;i++) a[first+i] = temp[i];

		System.out.println(mk++ +":"+Arrays.toString(a));

	}


}
