package com.haizhen;

import java.util.Arrays;

import com.haizhen.sort.Sort;
import com.haizhen.sort.cmp.BubbleSort3;
import com.haizhen.sort.cmp.HeapSort;
import com.haizhen.sort.cmp.InsertionSort1;
import com.haizhen.sort.cmp.InsertionSort2;
import com.haizhen.sort.cmp.InsertionSort3;
import com.haizhen.sort.cmp.InsertionSort4;
import com.haizhen.sort.cmp.MergeSort;
import com.haizhen.sort.cmp.QuickSort;
import com.haizhen.sort.cmp.SelectionSort;
import com.haizhen.sort.cmp.ShellSort;
import com.haizhen.tools.Asserts;
import com.haizhen.tools.Integers;
@SuppressWarnings({"unchecked","rawtypes"}) 
public class Main {

	public static void main(String[] args) {

		Integer[] array = Integers.random(20000, 1, 20000);

		testSorts(array, 
				new HeapSort(),
//				new BubbleSort3(), 
//				new SelectionSort(),
//				new InsertionSort1(),
//				new InsertionSort2(),
				new InsertionSort4(),
				new MergeSort(),
				new QuickSort(),
				new ShellSort()
				);

	}

	static void testSorts(Integer[] array,  Sort... sorts) {
		for (Sort sort : sorts) {
			Integer [] newArray=Integers.copy(array);
			sort.sort(newArray);
			Asserts.test(Integers.isAscOrder(newArray));

		}
		Arrays.sort(sorts);
		for (Sort<Integer> sort : sorts) {
			System.out.println(sort);
		}

	}

	/**
	 * 选择排序
	 * 
	 * 
	 * 选择排序的性能要比冒泡排序的性能要看点
	 */
	static void selectionSort(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			// 假设0这个位置是最大的
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (array[maxIndex] <= array[begin]) {
					maxIndex = begin;
				}

			}
			// 最大值
			int tmp = array[maxIndex];
			array[maxIndex] = array[end];
			array[end] = tmp;

		}
	}

	static void bubbleSorts3(Integer[] array) {
		for (int end = array.length - 1; end > 0; end--) {
			// sortedIndex 的初始化值在数据完全有序的时候有用
			// 当数组完全有序的时候 , 可以保证只有一个循环 ,然后就可以退出循环
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
