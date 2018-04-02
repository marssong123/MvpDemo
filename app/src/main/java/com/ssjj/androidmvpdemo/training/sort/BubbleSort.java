package com.ssjj.androidmvpdemo.training.sort;

/**
 * 冒泡排序<br>
 * 时间复杂度: 平均情况与最差情况都是O(n^2)<br>
 * 空间复杂度: O(1)
 * @author weijielu
 * @see ISort
 * @see SortTest
 */
public class BubbleSort implements ISort {

	public static void main(String[] args) {
		int[] a ={2,56,3,29,7,4,24,15,11};
		sort1(a);
	}

	public static void sort1(int[] array) {
		int temp = 0;
		for(int i = 0; i < array.length; i++){
			for(int j = 0; j < array.length - 1; j++){
				if(array[j] > array[j + 1]){
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+",");
		}
	}

	/**
	 * 对数组array进行升序排序
	 *
	 * @param array
	 */
	@Override
	public void sort(int[] array) {

	}
}
