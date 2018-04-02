package com.ssjj.androidmvpdemo.training;

/**
 * 二分查找的实现： 在一个有序数组中查找某个值
 * @author weijielu
 * @see
 */
public class BinarySearch {



	public static void main(String[] args) {
		int k = 6;
		int[] ins = new int[] { 8, 6, 10,9,7, 2 ,1,20,13};
//		scheme1(ins, k);
		System.out.print(indexOf("dsaffffffffffffffffffff" , "af"));
	}


	/**
	 * 使用循环的方式实现二分查找
	 * @param array
	 * @param value
	 * @return
	 */
	public static Integer searchCirculation(int[] array, int value){
		int low = 0;
		int high = array.length - 1;
		int middle;
		
		while(low <= high){
			middle = (low + high) / 2;
			if(value < array[middle]){
				high = middle - 1;
			}else if(value > array[middle]){
				low = middle + 1;
			}else{
				return array[middle];
			}
		}
		
		return null;
	}
	
	/**
	 * 使用递归的方式实现二分查找
	 * @param array
	 * @param value
	 * @return
	 */
	public static Integer searchRecursive(int[] array, int value){
		return searchRecursive(array, value, 0, array.length - 1);
	}
	
	private static Integer searchRecursive(int[] array, int value, int low, int high){
		if(high < low){
			return null;
		}
		
		int middle = (low + high) / 2;
		
		if(value < array[middle]){
			return searchRecursive(array, value, low, middle - 1);
		}else if(value > array[middle]){
			return searchRecursive(array, value, middle + 1, high);
		}else {
			return array[middle];
		}
	}


	/**
	 * 思路 一快排
	 * 思路 二
	 * 把整个数组分为k和n-k 2部分,找出最小的K个数的过程其实就是把最大的数放到n-k部分的过程,每次比较都把最大的数交换到n-k的部分里面。
	 * 1.把最先遍历到的k个数赋值到大小为k的数组2中
	 * 2.在数组2中找出最大元素max,时间复杂度是o(k),因为如果
	 * 3.在数组1中遍历剩下的n-k个数,和max比较,如果小于max则交换位置,重复2的过程 o(k)+(n-k)o(k)=n*o(k)
	 **/
	public static void scheme1(int[] ins, int k) {
		int[] ks = new int[k];
		// 最先遍历的k个数放入数组中 o(k)
		for (int i = 0; i < k; i++) {
			ks[i] = ins[i];
		}
		for (int i = k; i < ins.length; i++) {
			if (getMax(ks) > ins[i]) {
				ks[0] = ins[i];
			}
		}
		// 输出最小的K个数
		for (int i = 0; i < k; i++) {
			System.out.print(ks[i] + " ");
		}
	}
	public static int getMax(int[] arr) {
		// 选择一个基数,分别于数组中其他元素比较,始终保持基数对应的指针是最大值
		int radix = 0;
		for (int i = 0; i < arr.length; i++) {
			// 如果sub小于旁边值则交互位置
			if (arr[radix] < arr[i]) {
				int temp = arr[radix];
				arr[radix] = arr[i];
				arr[i] = temp;
			}
		}
		return arr[radix];
	}


	/**
	 * 字符串匹配 朴素算法
	 * @param source
	 * @param pattern
	 * @return
	 */
	public static int indexOf(String source, String pattern) {
		int i = 0, j = 0;
		int sLen = source.length(), pLen = pattern.length();
		char[] src = source.toCharArray();
		char[] ptn = pattern.toCharArray();
		while (i < sLen && j < pLen) {
			if (src[i] == ptn[j]) {
				// 如果当前字符匹配成功,则将两者各自增1,继续比较后面的字符
				i++;
				j++;
			} else {
				// 如果当前字符匹配不成功,则i回溯到此次匹配最开始的位置+1处,也就是i = i - j + 1
				// (因为i,j是同步增长的), j = 0;
				i = i - j + 1;
				j = 0;
			}
		}
		// 匹配成功,则返回模式字符串在原字符串中首次出现的位置;否则返回-1
		if (j == pLen)
			return i - j;
		else
			return -1;
	}



}
