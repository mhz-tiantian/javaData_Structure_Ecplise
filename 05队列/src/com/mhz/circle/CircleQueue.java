package com.mhz.circle;

/**
 * 循环对列
 * 
 * @author mahaizhen
 *
 * @date 2020年5月14日
 */
@SuppressWarnings("unchecked")
public class CircleQueue<E> {

	// 表示当前头元素的位置(坐标)
	private int front;
	// 存储当前队列有多少元素
	private int size;

	/**
	 * 存放我们真实数据的数组
	 */
	private E[] elements;

	private static final int DEFAULT_CAPACITY = 10;

	public CircleQueue() {
		elements = (E[]) new Object[DEFAULT_CAPACITY];
	}

	/**
	 * 
	 * @return 返回队列当前元素的个数
	 */
	public int size() {
		return size;

	}

	/**
	 * 是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {

		return size == 0;
	}

	/**
	 * 入队 添加到尾部 元素
	 * 
	 * @param element
	 */
	public void enQueue(E element) {

		ensureCapacity(size + 1);
		elements[index(size)] = element;
		size++;

	}

	/**
	 * 出队 从队头出队
	 * 
	 * @return
	 */
	public E deQueue() {
		E frontElement = elements[front];
		elements[front] = null;
		// front 队头往后移动
		front = index(1);
		size--;
		return frontElement;
	}

	/**
	 * 获取队列 头部的元素
	 * 
	 * @return
	 */
	public E front() {
		return elements[front];
	}

	@Override
	public String toString() {
		/// 打印形式为: size=5, [99, 88, 77, 66, 55]
		StringBuffer sb = new StringBuffer();
		sb.append("size=");
		sb.append(size);
		sb.append("  ");
		sb.append("front");
		sb.append(front);
		sb.append(",[");
		for (int i = 0; i < elements.length; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(elements[i]);

		}
		sb.append("]");
		System.out.println(sb);
		return sb.toString();
	}

	/**
	 * 返回真实的下标
	 * 
	 * @param index 转入的下标 
	 * @return
	 */
	private int index(int index) {
		return (index + front) % elements.length;

	}

	/**
	 * 每次添加的时候去检查 当前的数组的长度 是否满足需求, 如果不满足需求, 我们就去 开辟新的内存地址, 并把之前的数组, 复制到新的数组中,
	 * 让elements指向新的内存地址
	 */
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
		for (int i = 0; i < size; i++) {
			// 把elements中的元素 赋值到新的数组里面
			newElements[i] = elements[index(i)];
		}
		// 把新的数组内存地址, 指向旧的数组(elements)
		elements = newElements;
		// 重置front因为你把元素 重新移动位置了， 所以front 是要重置的
		front = 0;
		System.out.println("size=" + oldCapacity + ", 扩容到了" + newCapacity);

	}
}
