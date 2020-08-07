package com.haizhen.sort.cmp;

import com.haizhen.sort.Sort;

/**
 * 堆排序
 * 
 * @author mahaizhen
 *
 * @date 2020年8月3日
 */
public class HeapSort<E extends Comparable<E>> extends Sort<E> {
	private int heapSize;

	@Override
	protected void sort() {
		heapSize = array.length;
		for (int i = (heapSize >> 1) - 1; i >= 0; i--) {
			siftDown(i);
		}
		// 原地建堆
		while (heapSize > 1) {
			swap(0, --heapSize);
			siftDown(0);
		}
	}

	private void siftDown(int index) {
		E element = array[index];
		int half = heapSize >> 1;
		// index 必须是非叶子节点
		while (index < half) {
			int childIndex = (index << 1) + 1;
			E child = array[childIndex];
			int rightIndex = childIndex + 1;
			if (rightIndex < heapSize && cmp(array[rightIndex], child) > 0) {
				child = array[childIndex = rightIndex];

			}
			if (cmp(element, child) >= 0) {
				break;
			}
			array[index] = child;
			index = childIndex;
		}
		array[index] = element;

	}

}
