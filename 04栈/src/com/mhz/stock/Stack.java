package com.mhz.stock;

import com.mhz.base.List;
import com.mhz.link.CircleDoubleLinkedList;

/**
 * 栈 的数据结构,
 * 
 * @author mahaizhen
 *
 * @date 2020年5月13日
 * 
 *       简单介绍下 1. 栈也是个线性的数据结构 2. 先进后出的模式 3. 只允许在栈顶 操作元素
 */
public class Stack<T> {

	private List<T> stackList = new CircleDoubleLinkedList<>();

	public boolean isEmpty() {
		return stackList.isEmpty();

	};

	/**
	 * 往栈里添加元素
	 * 
	 * @param t
	 */
	public void push(T t) {
		stackList.add(t);
	};

	/**
	 * 弹出 栈顶元素
	 * 
	 * @return
	 */
	public T pop() {
		return stackList.remove(stackList.getSize() - 1);
	};

	/**
	 * 偷偷的看一眼栈顶的元素 不是, 弹出栈顶
	 * 
	 * @return
	 */
	public T peek() {
		return stackList.get(stackList.getSize() - 1);
	};

}
