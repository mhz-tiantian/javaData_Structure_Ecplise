package com.haizhen;

import com.haizhen.tools.Asserts;

public class BinarySearch {

	public static void main(String[] args) {
		int[] array = { 2, 3, 4, 5, 6, 7, 8, 10, 12, 14 };
//		Asserts.test(indexOf(array, 2) == 0);
//		Asserts.test(indexOf(array, 4) == 2);
//		Asserts.test(indexOf(array, 1) == -1);
//		Asserts.test(indexOf(array, 8) == 6);
		Asserts.test(search(array, 1) == 0);
		Asserts.test(search(array, 9) == 7);
		Asserts.test(search(array, 11) == 8);
	}

	/**
	 * 利用二分查找, 找到v 在arry中的位置, 如果没有就返回-1
	 * 
	 * @param array
	 * @param v
	 * @return
	 */
	public static int indexOf(int[] array, int v) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int begin = 0;
		int end = array.length;
		while (begin < end) {
			// 除以2 并且是向下取整
			int mid = (begin + end) >> 1;
			if (v < array[mid]) {
				end = mid;
			} else if (v > array[mid]) {
				begin = mid + 1;
			} else {
				return mid;
			}

		}
		// -1表示没有找到
		return -1;
	}

	/**
	 * 利用二分查找, 元素待插入的位置
	 * 
	 * @param array
	 * @param v
	 * @return 返回的值为 待插入的位置
	 */
	public static int search(int[] array, int v) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int begin = 0;
		int end = array.length;
		while (begin < end) {
			int mid = (begin + end) >> 1;
			if (v < array[mid]) {
				end = mid;
			} else {
				// v>=array[mid]
				begin = mid + 1;
			}

		}
		return begin;
	}

}
