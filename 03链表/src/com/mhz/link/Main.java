package com.mhz.link;

public class Main {

	public static void main(String[] args) {

		List<Integer> linkedList = new LinkedList<Integer>();
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		linkedList.add(2, 9);
		linkedList.set(2, 11);

		linkedList.remove(2);
		linkedList.clear();
//		linkedList.toString();

		List<Integer> arrayList = new ArrayList<Integer>();
		arrayList.add(2);
		arrayList.add(34);
		arrayList.add(37);
		arrayList.add(39);
		int old = arrayList.set(2, 89);
		System.out.println("替换后的元素为" + old);
		arrayList.toString();

	}

}
