package com.haizhen.sort;

/**
 * 基数排序
 * 
 * @author mahaizhen
 *
 * @date 2020年8月10日
 */
public class RadixSort extends Sort<Integer> {

	@Override
	protected void sort() {
		// 1.首选找出最大值
		int max = array[0];
		for (int i = 1; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}
		// 个位数: array[i] / 1 % 10 = 3
		// 十位数：array[i] / 10 % 10 = 9
		// 百位数：array[i] / 100 % 10 = 5
		// 千位数：array[i] / 1000 % 10 = ...
		for (int divider = 1; divider <= max; divider *= 10) {
			countingSort(divider);
		}

	}

	private void countingSort(int divider) {
		// 开辟一个新的内存空间
		int[] counts = new int[10];
		// 统计每个整数出现的次数
		for (int i = 0; i < array.length; i++) {
			// array[i] / divider % 10 这个求出每位数上  的value
			counts[array[i] / divider % 10]++;
		}
		// 累加次数
		for (int i = 0; i < counts.length; i++) {
			counts[i] += counts[i - 1];
		}
		
		int[] newArray = new int[array.length];
		for (int i = array.length - 1; i >= 0; i--) {
			newArray[--counts[array[i] / divider % 10]] = array[i];
		}
		// 把newArray里面的数据覆盖到array
		for (int i = 0; i < newArray.length; i++) {
			array[i] = newArray[i];
		}

	}

}
