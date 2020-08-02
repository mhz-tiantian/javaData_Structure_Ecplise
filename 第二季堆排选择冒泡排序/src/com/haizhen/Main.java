package com.haizhen;

import com.haizhen.tools.Integers;
import com.haizhen.tools.Times;

public class Main {

	public static void main(String[] args) {
		Integer[] array1 = Integers.random(10000, 1, 90000);
		Integer[] array2 = Integers.copy(array1);
		Times.test("bubbleSorts1", () -> {
			bubbleSorts1(array1);
		});

		
		
		Times.test("bubbleSorts2", () -> {
			bubbleSorts2(array2);
		});

	}

	static void bubbleSorts3(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			int sortedIndex = 1;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
					sortedIndex = begin;
				}
			}
			end = sortedIndex;

		}

	}

	static void bubbleSorts2(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			// 已经开始有序了
			boolean sorted = true;
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
					sorted = false;
				}

				if (sorted) {
					// 退出全部的循环
					// 数组已经有序了
					break;
				}

			}

		}

	}

	static void bubbleSorts1(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			for (int begin = 1; begin <= end; begin++) {
				if (array[begin] < array[begin - 1]) {
					int tmp = array[begin];
					array[begin] = array[begin - 1];
					array[begin - 1] = tmp;
				}
			}
		}
	}

}
