package com.mhz.link;

import com.mhz.link.circle.CircleDoubleLinkedList;

public class Main {

	public static void main(String[] args) {
//
//		List<Integer> linkedList = new LinkedList<Integer>();
//		linkedList.add(2);
//		linkedList.add(3);
//		linkedList.add(4);
//		linkedList.add(2, 9);
//		linkedList.set(2, 11);
//
//		linkedList.remove(2);
//		linkedList.clear();
////		linkedList.toString();
//
//		List<Integer> arrayList = new ArrayList<Integer>();
//		arrayList.add(2);
//		arrayList.add(34);
//		arrayList.add(37);
//		arrayList.add(39);
//		int old = arrayList.set(2, 89);
//		System.out.println("替换后的元素为" + old);
//		arrayList.toString();

//		testTrim();

		yuesefu(2);
	}

	/**
	 * 约瑟夫 问题
	 */
	private static void yuesefu(int sum) {
		CircleDoubleLinkedList<Integer> list = new CircleDoubleLinkedList<Integer>();
		for (int i = 1; i <= 8; i++) {
			list.add(i);

		}
		// 当前的节点, 指向头节点
		list.reset();
		while (!list.isEmpty()) {
			int index = sum;
			while (index-- > 0) {
				list.next();
			}
			Integer remove = list.remove();
			System.out.println("出栈的顺序为" + remove);
		}

//		while (!list.isEmpty()) {
//			list.next();
//			list.next();
//			Integer remove = list.remove();
//			System.out.println("出栈的顺序为" + remove);
//		}

	}

	/**
	 * 测试下 ArrayList 的缩容问题
	 */
	private static void testTrim() {
		List<Integer> arrayList = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			arrayList.add(i);
		}

		for (int i = 0; i < 50; i++) {
			arrayList.remove(0);
		}

	}

}
