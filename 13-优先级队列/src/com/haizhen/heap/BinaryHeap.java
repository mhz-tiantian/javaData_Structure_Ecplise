package com.haizhen.heap;

import java.util.Comparator;

import com.haizhen.printer.BinaryTreeInfo;

@SuppressWarnings("unchecked")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {
	private E[] elements;
	private static final int DEFAULT_CAPACITY = 10;

	public BinaryHeap(Comparator<E> comparator) {
		this(null, comparator);

	}

	public BinaryHeap(E[] elements, Comparator<E> comparator) {
		super(comparator);
		if (elements == null || elements.length == 0) {
			this.elements = (E[]) new Object[DEFAULT_CAPACITY];
		} else {
			size=elements.length;
			int capacity = Math.max(elements.length, DEFAULT_CAPACITY);
			this.elements = (E[]) new Object[capacity];
			for (int i = 0; i < elements.length; i++) {
				this.elements[i] = elements[i];
			}
			// 批量创建堆
			heapify();
		}
	}

	public BinaryHeap(E[] elements) {
		this(elements, null);
	}

	public BinaryHeap() {
		this(null, null);
	}

	@Override
	public void clear() {
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;

	}

	@Override
	public void add(E element) {
		elementNotNullCheck(element);
		ensureCapacity(size + 1);
		// 往最后面添加元素
		elements[size++] = element;
		// 上滤
		siftUp(size - 1);

	}

	@Override
	public E get() {
		emptyCheck();
		return elements[0];
	}

	@Override
	public E remove() {
		emptyCheck();
//		int lastIndex = --size;
		E root = elements[0];
		elements[0] = elements[size - 1];
		elements[size - 1] = null;
		size--;
		siftDown(0);
		return root;
	}

	@Override
	public E replace(E element) {
		elementNotNullCheck(element);
		E root = null;
		if (size == 0) {
			elements[0] = element;
			size++;
		} else {
			root = elements[0];
			elements[0] = element;
			siftDown(0);
		}
		return root;
//		E root = remove();
//		add(element);
//		return root;
	}

	/**
	 * 让index 位置的元素下滤
	 * 
	 * @param index
	 */
	private void siftDown(int index) {
		// index 的元素必须有子节点
		// 必须保证index位置是非叶子节点
		// 第一个叶子节点的索引 == 非叶子节点的个数
		int half = size >> 1; // size的一半 向下取整
		E element = elements[index];
		while (index < half) {
			// index 的节点有2中情况
			// 1.只有左子节点
			// 2.同时有左右子节点
			// 默认为左子节点 跟它比较
			int childIndex = (index << 1) + 1;
			E child = elements[childIndex];
			// 右子节点
			int rightIndex = childIndex + 1;
			if (rightIndex < size && compare(elements[rightIndex], child) > 0) {
				childIndex = rightIndex;
				child = elements[childIndex];

			}
			if (compare(element, child) >= 0) {
				break;
			}
			// 将子节点存放到index位置
			elements[index] = child;
			// 重新设置index
			index = childIndex;
		}
		elements[index] = element;

	}

	private void heapify() {
		// 自上而下的上滤
//		for (int i = 1; i <size; i++) {
//			siftUp(i);
//		}
		// 自下而上的下滤
		for (int i = (size >> 1) - 1; i >=0; i--) {
			siftDown(i);
		}
	}

	/**
	 * 让index 位置的元素上滤
	 * 
	 * @param index
	 */
	private void siftUp(int index) {
		// 取出自己的元素
//		E e = elements[index];
//		while (index > 0) {
//			int pIndex = (index - 1) >> 1;
//			E p = elements[pIndex];
//			// 小于等于父节点的内容
//			if (compare(e, p) <= 0) {
//				return;
//			}
//			// 交换 p. e
//			E tmp = elements[index];
//			elements[index] = elements[pIndex];
//			elements[pIndex] = tmp;
//			// 交换index 去保证下次循环的条件 判断
//			index = pIndex;
//		}
		// 取出自己的元素
		E element = elements[index];
		while (index > 0) {
			int parentIndex = (index - 1) >> 1;
			E parentElement = elements[parentIndex];
			if (compare(element, parentElement) <= 0) {
				// break 退出循环,
				// return 返回方法
				break;
			}
			// 将父元素存储在index 位置上
			elements[index] = parentElement;
			index = parentIndex;
		}
		elements[index] = element;

	}

	private void ensureCapacity(int capacity) {
		// 这里 oldCapacity 还不能是我们定义常量 DEFAULT_CAPACITY
		// 如果是我们定义的常量的话, 那么下一次再需要去扩容的时候,
		// oldCapacity 就是一个 固定的值, 所以 newCapacity也是一个固定的值,
		// 就不会扩容 ,方法的最后 elements = newElements;这句话也是很重要的,
		// 这句话 ,就导致了 oldCapacity 是一个变化的长度 , 不是一个固定的长度
		int oldCapacity = elements.length;
		// 如果 旧的长度大于 需要的长度 直接返回
		if (oldCapacity >= capacity) {
			return;
		}
		// 位运算 , 新的长度是旧的长度的1.5倍 oldCapacity >> 1
		// 就是oldCapacity*0.5 然后在加上oldCapacity
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		E[] newElements = (E[]) new Object[newCapacity];
		for (int i = 0; i < elements.length; i++) {
			// 把elements中的元素 赋值到新的数组里面
			newElements[i] = elements[i];
		}
		// 把新的数组内存地址, 指向旧的数组(elements)
		elements = newElements;
//		System.out.println("size=" + oldCapacity + ", 扩容到了" + newCapacity);

	}

	@Override
	public Object root() {
		return 0;
	}

	@Override
	public Object left(Object node) {
		Integer index = (Integer) node;
		index = (index << 1) + 1;
		return index >= size ? null : index;
	}

	@Override
	public Object right(Object node) {
		Integer index = (Integer) node;
		index = (index << 1) + 2;
		return index >= size ? null : index;
	}

	@Override
	public Object string(Object node) {
		Integer index = (Integer) node;
		return elements[index];
	}
}
