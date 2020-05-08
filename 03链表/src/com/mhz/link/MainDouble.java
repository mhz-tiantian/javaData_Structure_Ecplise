package com.mhz.link;

public class MainDouble {

	public static void main(String[] args) {
		List<Integer> doubleList = new DoubleLinkedList<Integer>();
		doubleList.add(null);
		doubleList.add(2);
		doubleList.add(3);
		doubleList.add(43);
		doubleList.add(58);
		doubleList.add(65);

//		doubleList.remove(2);
//		doubleList.remove(0);
		doubleList.remove(doubleList.getSize() - 1);
		System.out.println("2 的索引为" + doubleList.indexOf(null));
		
		System.out.println("3的位置上的值为"+doubleList.get(3));
		doubleList.set(2, 56);

		doubleList.toString();
	}
}
