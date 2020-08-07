package com.haizhen.sort.cmp;

import com.haizhen.sort.Sort;

/**
 * 选择排序
 * 
 * @author mahaizhen
 *
 * @date 2020年8月6日
 */
public class SelectionSort<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		for (int end = array.length - 1; end > 0; end--) {
			// 假设0这个位置是最大的
			int maxIndex = 0;
			for (int begin = 1; begin <= end; begin++) {
				if (cmp(maxIndex, begin) <= 0) {
					maxIndex = begin;
				}
			}
			swap(maxIndex, end);
		}
	}

}
