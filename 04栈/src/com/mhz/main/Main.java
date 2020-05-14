package com.mhz.main;

import com.mhz.stock.Stack;

public class Main {

	private static void stackTest() {
		Stack<Integer> stack = new Stack<Integer>();
		stack.push(22);
		stack.push(33);
		stack.push(44);
		stack.push(55);
		while (!stack.isEmpty()) {
			System.out.println("弹出的顺序为"+stack.pop());
				
		}
	}

	public static void main(String[] args) {
		stackTest();
	}
}
