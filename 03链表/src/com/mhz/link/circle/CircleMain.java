package com.mhz.link.circle;

import com.mhz.link.List;

public class CircleMain {

	public static void main(String[] args) {
		List<Integer> circleList = new CircleSingleLinkedList<Integer>();
		circleList.add(21);
		circleList.add(56);
		circleList.add(78);
		circleList.add(85);
		circleList.add(0, 69);
		circleList.toString();
	}
}
