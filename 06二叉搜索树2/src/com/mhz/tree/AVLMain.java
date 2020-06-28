package com.mhz.tree;

import com.mhz.tree.printer.BinaryTrees;

public class AVLMain {

	private static void test1() {
		Integer[] data = { 63, 19, 12, 72, 69, 78, 38, 41, 61, 36, 33, 76, 31 };

		BST<Integer> trees = new AVLTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			trees.add(data[i]);
		}
		BinaryTrees.print(trees);

	}

	public static void main(String[] args) {
		test1();
	}
}
