package 链表;

import java.util.Stack;

public class _232_用栈实现队列 {

	// 入栈的操作
	Stack<Integer> inStack = new Stack<>();
	// 出栈的操作
	Stack<Integer> outStack = new Stack<>();

	/**
	 * 往队列里去添加元素
	 * 
	 * @param x
	 */
	public void push(int x) {
		inStack.add(x);

	}

	public int pop() {
		// 如果是
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
			return outStack.pop();
		}
		return outStack.pop();

	}

	public int peek() {
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
			return outStack.peek();
		}
		return outStack.peek();
	}

	public boolean empty() {
		return outStack.isEmpty() && inStack.isEmpty();
	}

	public static void main(String[] args) {
		_232_用栈实现队列 queue = new _232_用栈实现队列();
		queue.push(1);
		queue.push(2);
		System.out.println(queue.peek()); // 返回 1
		System.out.println(queue.pop()); // 返回 1
		System.out.println(queue.empty()); // false

	}

}
