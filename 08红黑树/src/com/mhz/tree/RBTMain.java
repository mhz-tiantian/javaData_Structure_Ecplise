package com.mhz.tree;

import com.mhz.tree.printer.BinaryTrees;

public class RBTMain {
	private static void test1() {
		Integer[] data = { 5, 71, 31, 97, 65, 42, 36, 100, 89, 72, 81, 82, 24, 59, 56, 17, 75, 50 };

		BST<Integer> trees = new RBTree<Integer>();
		for (int i = 0; i < data.length; i++) {
			trees.add(data[i]);
		}
		BinaryTrees.print(trees);

	}

	public static void main(String[] args) {
		test1();
	}
}
