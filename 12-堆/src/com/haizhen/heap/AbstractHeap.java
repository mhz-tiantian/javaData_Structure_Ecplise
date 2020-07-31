package com.haizhen.heap;

import java.util.Comparator;

public abstract class AbstractHeap<E> implements Heap<E> {
	protected int size;
	protected Comparator<E> comparator;

	public AbstractHeap(Comparator<E> comparator) {
		this.comparator = comparator;

	}

	public AbstractHeap() {
		this(null);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	
	protected void emptyCheck() {
		if (size == 0) {
			throw new IndexOutOfBoundsException("heap is empty");

		}
	}

	protected void elementNotNullCheck(E element) {
		if (element == null) {
			throw new IllegalArgumentException("element must not be null");

		}
	}

	/**
	 *  
	 * @param e1
	 * @param e2
	 * @return 返回值大于0  说明 e1 大于e2  反之 则说明e1 小于e2
	 */
	protected int compare(E e1, E e2) {
		return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>) e1).compareTo(e2);
	}

}
