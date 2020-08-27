package com.haizhen.sort;

/**
 * 计数排序
 * 
 * @author mahaizhen
 *
 * @date 2020年8月7日
 */
public class CountingSort extends Sort<Integer> {

	/**
	 *  这个排序就可以对负整数进行排序
	 */
	@Override
	protected void sort() {
		int min = array[0];
		int max = array[0];
		// 拿到数组中的最大值, 和最小值
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
			if (array[i] > max) {
				max = array[i];

			}
		}
		// 开辟内存空间,
		int[] counts = new int[max - min + 1];
		// 对array数组中的值, 进行计数
		for (int i = 0; i < array.length; i++) {
			counts[array[i] - min]++;
		}
		// 处理 counts 里面的数据
		for (int i = 1; i < counts.length; i++) {
			counts[i] += counts[i - 1];
		}

		int[] newArray = new int[array.length];
		for (int i = array.length - 1; i >= 0; i--) {
			newArray[--counts[array[i] - min]] = array[i];
		}
		// 把newArray里面的数据覆盖到array
		for (int i = 0; i < newArray.length; i++) {
			array[i] = newArray[i];
		}

	}

	/**
	 * 这个方式的计数排序, 对内存空间的浪费很多 而且 不能对 负整数进行排序, 并且不稳定 上面那个是 优化的方式,
	 */
	private void sort0() {
		// 假设最大值是 arr[0]这个位置
		int max = array[0];
		// 找出最大值
		for (int i = 1; i < array.length; i++) {
			if (max < array[i]) {
				max = array[i];
			}
		}

		// 开辟一个新的内存空间
		int[] counts = new int[max + 1];
		// 统计每个数出现的 次数
		for (int i = 0; i < array.length; i++) {
			// 取出每个元素 在counts的位置 , 并且在counts位置的值加1
			counts[array[i]]++;
		}
		// 建立一个临时的指针
		int index = 0;
		for (int i = 0; i < counts.length; i++) {
			while (counts[i]-- > 0) {
				array[index] = i;
				index++;
			}

		}

	}

}
