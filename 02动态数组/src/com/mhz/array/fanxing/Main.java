package com.mhz.array.fanxing;

public class Main {

	public static void main(String[] args) {
		
		Person person = new Person(1, "测试1");
		ArrayList<Person> persArrayList = new ArrayList<Person>();
		persArrayList.add(person);
		person = new Person(2, "测试2");
		persArrayList.add(person);
		persArrayList.toString();
	}

}
