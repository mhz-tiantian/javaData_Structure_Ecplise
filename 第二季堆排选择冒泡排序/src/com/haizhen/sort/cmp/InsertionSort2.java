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
public class InsertionSort2<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		for (int begin = 1; begin < array.length; begin++) {
			int cur = begin;
			E v = array[cur];
			while (cur > 0 && cmp(v, array[cur - 1]) < 0) {
				array[cur] = array[cur - 1];
				cur--;
			}
			// 把当前的元素 插入到合适的位置
			array[cur] = v;
		}

	}

}
