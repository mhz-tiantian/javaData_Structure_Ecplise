package com.haizhen.sort.cmp;

import com.haizhen.sort.Sort;

/**
 * 归并排序
 * 
 * 
 * @author mahaizhen
 *
 * @date 2020年8月6日
 */
public class MergeSort<E extends Comparable<E>> extends Sort<E> {

	private E[] leftArray;

	@Override
	protected void sort() {
		// 先把左边的数组进行初始化
		leftArray = (E[]) new Comparable[array.length >> 1];
		sort(0, array.length);
	}

	/**
	 * 对 [begin end)范围的数据进行归并排序
	 * 
	 * @param begin
	 * @param end
	 */
	private void sort(int begin, int end) {
		if (end - begin < 2) {
			return;
		}
		int mid = (begin + end) >> 1;
		sort(begin, mid);
		sort(mid, end);
		merge(begin, mid, end);
	}

	private void merge(int begin, int mid, int end) {
		int li = 0;
		int le = mid - begin; // 左边数组(基于leftArray)
		int ri = mid;
		int re = end;// 右边的数组( array)
		int ai = begin;// array的索引
		// 备份左边数组到LeftArray
		for (int i = li; i < le; i++) {
			leftArray[i] = array[begin + i];
		}

		// 如果左边还没有结束
		while (li < le) {
			// array[ri]<leftarray[li]
			 // cmp改为<=0会失去稳定性
			if (ri < re && cmp(array[ri], leftArray[li]) < 0) {
				array[ai++] = array[ri++];
				//右边<左边 拷贝右边数组到array
			} else {
				// 左边<=右边 拷贝左边数组到array
				array[ai++] = leftArray[li++];
			}

		}
	}

}
