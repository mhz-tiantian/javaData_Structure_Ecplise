package com.haizhen.queue;

import java.awt.HeadlessException;
import java.util.Comparator;

import com.haizhen.heap.BinaryHeap;

/**
 * 利用二叉堆实现 优先级队列
 * 
 * @author mahaizhen
 *
 * @date 2020年7月31日
 */
public class PriorityQueue<E> {

	private BinaryHeap<E> heap;

	/**
	 * 自己去定义 优先级队列的 优先规则, 所以必须是可以比较的元素
	 * 
	 * @param comparator
	 */
	public PriorityQueue(Comparator<E> comparator) {
		heap = new BinaryHeap<>(comparator);
	}

	public PriorityQueue() {
		this(null);
	}

	public int size() {
		return heap.size();
	}

	public boolean isEmpty() {
		return heap.isEmpty();
	}

	public void clear() {
		heap.clear();
	}

	/**
	 * 入队
	 * 
	 * @param element
	 */
	public void enQueue(E element) {
		heap.add(element);
	}

	/**
	 * 出队
	 * 
	 * @return
	 */
	public E deQueue() {
		return heap.remove();
	}

	public E front() {
		return heap.get();
	}
}
