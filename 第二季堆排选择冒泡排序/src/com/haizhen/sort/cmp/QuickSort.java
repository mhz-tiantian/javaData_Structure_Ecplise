package com.haizhen.sort.cmp;

import com.haizhen.sort.Sort;

/**
 *  快速排序 , 不稳定的排序
 * @author mahaizhen
 *
 * @date 2020年8月6日
 */
public class QuickSort<E extends Comparable<E>> extends Sort<E> {

	@Override
	protected void sort() {
		sort(0, array.length);

	}

	private void sort(int begin, int end) {
		if (end - begin < 2) {
			return;
		}
		int pivotIndex = pivot(begin, end);
		sort(begin, pivotIndex);
		sort(pivotIndex + 1, end);

	}

	/**
	 * 构造出 [ begin end)范围中的轴点元素
	 * 
	 * @param begin
	 * @param end
	 * @return 返回轴点元素的最终的位置
	 */
	private int pivot(int begin, int end) {
		// 随机选择一个元素，跟begin位置进行交换
		swap(begin, (int) (begin + Math.random() * (end - begin)));

		// 备份begin 位置的元素
		E pivot = array[begin];
		end--;
		while (begin < end) {
			second: while (begin < end) {
				// 后面的元素 大于轴点的元素
				if (cmp(pivot, array[end]) < 0) { // 右边元素>轴点元素
					end--;
				} else { // 右边元素<=轴点元素
					// 拿到end位置的元素 去覆盖begin位置的元素 , 然后begin++
					array[begin++] = array[end];
					break second;
				}
			}

			second: while (begin < end) {
				// 左边元素< 轴点元素
				if (cmp(pivot, array[begin]) > 0) {
					begin++;
				} else {
					// 左边元素>=轴点元素
					// 拿到begin位置的元素 去覆盖end 位置的元素, 然后end--;
					array[end--] = array[begin];
					break second;
				}
			}

		}
		// 将轴点元素放入最终的位置
		array[begin] = pivot;
		// 返回轴点元素的位置
		return begin;
	}

}
