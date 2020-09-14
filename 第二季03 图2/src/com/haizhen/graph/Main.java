package com.haizhen.graph;

public class Main {

	public static void main(String[] agrs) {
//		int a = 8;
//		System.out.println( Integer.toBinaryString(a));
//		a = a << 3;
//		System.out.println("----------------------");
//		System.out.println("a===="+a);
//		System.out.println(Integer.toBinaryString(a));

//		int a = -8;
//		System.out.println(Integer.toBinaryString(a));
//		a = a >> 3;
//		System.out.println("----------------------");
//		System.out.println("a===="+a);
//		System.out.println(Integer.toBinaryString(a));

		// 32767
		short a = 31767;
		System.out.println(Integer.toBinaryString(a));
//		short b = a >> 8;
		System.out.println("----------------------");
		System.out.println(Integer.toBinaryString(a >> 8));
//		short c = a << 8;

		System.out.println("----------------------");
		System.out.println(Integer.toBinaryString(a << 8));
//		a = (a >> 8) | (a << 8);
		System.out.println("----------------------");
//		int d = b | c;
		a = (short) ((a >> 8) | (a << 8));

		System.out.println(Integer.toBinaryString(a));
	}

}
