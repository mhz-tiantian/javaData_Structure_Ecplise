package com.mhz.queue;

import com.mhz.link.CircleDoubleLinkedList;

/**
 * 双端队列
 * 
 * @author mahaizhen
 *
 * @date 2020年5月13日 1. 双端队列与普通队列最大的区别就是 双端队列, 允许 队尾队头添加删除元素
 * 
 * 
 *  队头 就是第一个元素, 队尾就是最后一个元素   
 */
public class DoubleQueue<T> {
	CircleDoubleLinkedList<T> linkedList = new CircleDoubleLinkedList<>();

	/**
	 * 返回队列的size
	 * 
	 * @return
	 */
	public int size() {
		return linkedList.getSize();
	}

	/**
	 * 返回队列是否为null
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return linkedList.isEmpty();
	}

	/**
	 * 从队尾入队
	 * 
	 * @param element
	 */
	public void enQueueRear(T element) {
		linkedList.add(element);
	}

	/**
	 * 从队尾出队
	 * 
	 * @return
	 */
	public T deQueueRear() {
		return linkedList.remove(size() - 1);

	}

	/**
	 * 从队头入队
	 */
	public void enQueueFront(T element) {
		linkedList.add(0, element);

	}

	/**
	 * 从队头出队
	 * 
	 * @return
	 */
	public T deQueueFront() {
		return linkedList.remove(0);

	}

	/**
	 * 获得队头的元素
	 * 
	 * @return
	 */
	public T front() {
		return linkedList.get(0);

	}

	/**
	 * 获取队尾的元素
	 * 
	 * @return
	 */
	public T rear() {
		return linkedList.get(size() - 1);

	}

}
