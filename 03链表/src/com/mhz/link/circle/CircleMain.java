package com.mhz.link.circle;

import com.mhz.link.List;

public class CircleMain {

	public static void main(String[] args) {
		textList(new CircleDoubleLinkedList<Integer>());

	}

	public static void textList(List<Integer> circleList) {
		circleList.add(21);
		circleList.add(56);
		circleList.add(78);
		circleList.add(85);
//		circleList.add(1, 69);// [21-69-56 78 85]
		circleList.toString();
		
		circleList.remove(0); // [69-56 78 85]   
		circleList.toString();

	}
}
