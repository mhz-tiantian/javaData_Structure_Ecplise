package com.haizhen;

import com.haizhen.heap.BinaryHeap;
import com.haizhen.printer.BinaryTrees;
import com.haizhen.queue.PriorityQueue;

public class Main {

	static void test1() {
		BinaryHeap<Integer> heap = new BinaryHeap<>();
		heap.add(68);
		heap.add(72);
		heap.add(43);
		heap.add(50);
		heap.add(38);

		BinaryTrees.print(heap);
	}
	
	static void test2() {
		PriorityQueue<Person> queue = new PriorityQueue<>();
		queue.enQueue(new Person("Jack", 2));
		queue.enQueue(new Person("Rose", 10));
		queue.enQueue(new Person("Jake", 5));
		queue.enQueue(new Person("James", 15));
		
		while (!queue.isEmpty()) {
			System.out.println(queue.deQueue());
		}
	}

	public static void main(String[] args) {
		
		test2();

	}

}
