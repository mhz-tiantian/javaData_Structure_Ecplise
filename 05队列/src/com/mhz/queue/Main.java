package com.mhz.queue;

import com.mhz.link.CircleDoubleLinkedList;

public class Main {

	public static void main(String[] args) {

		testDoubleQueue();
//		testDoubleQueue2();

	}
	
	private static void testDoubleQueue2() {
		CircleDoubleLinkedList<Integer> queue = new CircleDoubleLinkedList<>();
		queue.add(0,22);
		queue.add(0,33);
		System.out.println("出队列的顺序为" +queue.toString());

	}
	

	private static void testDoubleQueue() {
		DoubleQueue<Integer> queue = new DoubleQueue<>();
		queue.enQueueFront(22);
		queue.enQueueFront(33);
		queue.enQueueRear(44);
		queue.enQueueRear(55);
		queue.enQueueRear(66);

		// 66 55 44 22 33
		while (!queue.isEmpty()) {
			System.out.println("出队列的顺序为" + queue.deQueueFront());

		}
	}

	private static void testQueue() {
		Queue<Integer> queue = new Queue<Integer>();
		queue.enQueue(22);
		queue.enQueue(33);
		queue.enQueue(44);
		queue.enQueue(55);
		queue.enQueue(66);

		System.out.println("队列的头部为" + queue.front());
		while (!queue.isEmpty()) {
			System.out.println("出队列的顺序为" + queue.deQueue());

		}

	}
}
