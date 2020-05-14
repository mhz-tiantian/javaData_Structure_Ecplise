package com.mhz.queue;

import com.mhz.circle.CircleDeque;
import com.mhz.circle.CircleQueue;
import com.mhz.link.CircleDoubleLinkedList;

public class Main {

	public static void main(String[] args) {

//		testDoubleQueue();
//		testDoubleQueue2();

//		circleTest();

		circleTest3();
	}

	private static void circleTest3() {
		CircleDeque<Integer> queue = new CircleDeque<Integer>();
		for (int i = 0; i < 10; i++) {
			queue.enQueueFront(i + 1);
			queue.enQueueReal(i + 100);
		}

		for (int i = 0; i < 3; i++) {
			queue.deQueueFront();
			queue.deQueueReal();
		}
		queue.enQueueFront(11);
		queue.enQueueFront(12);
		queue.toString();
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueueFront());
		}

	}

	private static void circleTest() {
		CircleQueue<Integer> queue = new CircleQueue<Integer>();
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}

		for (int i = 0; i < 5; i++) {
			queue.deQueue();
		}
		for (int i = 15; i < 23; i++) {
			queue.enQueue(i);
		}
		queue.toString();
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}

	}

	private static void testDoubleQueue2() {
		CircleDoubleLinkedList<Integer> queue = new CircleDoubleLinkedList<>();
		queue.add(0, 22);
		queue.add(0, 33);
		System.out.println("出队列的顺序为" + queue.toString());

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
