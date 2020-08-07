package com.haizhen.sort.cmp;

import com.haizhen.sort.Sort;

/**
 * 插入排序 优化, 不要每次都是交换, 找到要插入的位置 每次都去移动,(如果当前的值, 比前一个值要小的话, 前一个值就后移, 一直找到比当前值大于的 ,
 * 或者移动到第一个位置的时候(等于0的时候))
 * 
 * @author mahaizhen
 *
 * @date 2020年8月4日
 */
public class InsertionSort4<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			insert(begin, search(begin));
		}

	}

	/**
	 * * 将source位置的元素插入到dest位置
	 * 
	 * @param source
	 * @param dest
	 */
	private void insert(int source, int dest) {
		E v = array[source];
		for (int i = source; i > dest; i--) {
			array[i] = array[i - 1];
		}
		array[dest] = v;

	}

	/**
	 * 利用二分查找 在有序的序列中, 查找 [0,index) 查找待插入的元素的位置
	 * 
	 * @param index
	 * @return
	 */
	private int search(int index) {
		if (array == null || array.length == 0) {
			return -1;
		}
		int begin = 0;
		int end = index;
		while (begin < end) {
			int mid = (begin + end) >> 1;
			if (cmp(array[index], array[mid]) < 0) {
				end = mid;
			} else {
				// v>=array[mid]
				begin = mid + 1;
			}

		}
		return begin;
	}

}
