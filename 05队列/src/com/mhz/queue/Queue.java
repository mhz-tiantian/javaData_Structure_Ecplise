package com.mhz.queue;

import com.mhz.link.CircleDoubleLinkedList;

/**
 * 队列
 * 
 * @author mahaizhen
 *
 * @date 2020年5月13日
 * 
 *       说明 1 队列也是线性结构的 
 *       2. 队列只能在 队尾或者队头来操作元素(只能在头尾两端进行操作)
 *       3.队尾(rear):只能从队尾添加元素,一般叫做enQueue 入队 
 *       4.队头(front) 只能从队头移除元素,
 *       一般叫做deQueue 出队
 *       5. 队列是先进先出的顺序来的
 * 
 */
public class Queue<T> {

	CircleDoubleLinkedList<T> cirLinkedList = new CircleDoubleLinkedList<>();

	public int size() {
		return cirLinkedList.getSize();

	}

	public boolean isEmpty() {

		return cirLinkedList.isEmpty();
	}

	/**
	 * 入队   添加到尾部 元素
	 * 
	 * @param element
	 */
	public void enQueue(T element) {
		cirLinkedList.add(element);

	}

	/**
	 * 出队  把尾部的点击
	 * 
	 * @return
	 */
	public T deQueue() {
		return cirLinkedList.remove(0);
	}

	/**
	 * 获取队列 头部的元素
	 * 
	 * @return
	 */
	public T front() {
		return cirLinkedList.get(0);

	}

}
