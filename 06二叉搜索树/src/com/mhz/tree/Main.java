package com.mhz.tree;

import com.mhz.tree.BinarySearchTree.Visitor;
import com.mhz.tree.printer.BinaryTrees;

public class Main {

	static void testTree() {
		Integer[] treeNodes = { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };
		

		BinarySearchTree<Integer> trees = new BinarySearchTree<>();
		for (int i = 0; i < treeNodes.length; i++) {
			trees.add(treeNodes[i]);
		}
		BinaryTrees.print(trees);
		System.out.println();

//		trees.preorderTraversal();

//		trees.inorderTraversal();
//		trees.postorderTraversal();
		// 7 ,4,9,2,5,8,11,1,3,12
//		trees.levelOrderTraversal();|
		
		System.out.println("=====前序遍历=====");
		trees.preorder(new Visitor<Integer>() {

			@Override
			boolean visitor(Integer element) {
				System.out.print(element+"\t");
				if (element.equals(new Integer(9)) ) {
					System.out.println("==9");
					return true;
				}else {
					return false;
				}
			
			}
		});
		System.out.println("=====中序遍历=====");
		trees.inorder(new Visitor<Integer>() {

			@Override
			boolean visitor(Integer element) {
				System.out.print(element+"\t");
				if (element == 9) {
					return true;
				}else {
					return false;
				}
				
				
				
			}
		});;

		System.out.println("=====后序遍历=====");
		trees.postorder(new Visitor<Integer>() {

			@Override
			boolean visitor(Integer element) {
				System.out.print(element+"\t");
				if (element == 9) {
					return true;
				}else {
					return false;
				}
				
				
			}
		});;

//		trees.levelOrder(new Visitor<Integer>() {
//
//			@Override
//			public boolean visitor(Integer element) {
//				System.out.println(element);
//				if (element == 9) {
//					return true;
//				}
//				return false;
//
//			}
//		});

	}

	public static void main(String[] args) {
		testTree();
	}

}
