package com.mhz.tree;

import com.mhz.tree.BinaryTree.Visitor;
import com.mhz.tree.printer.BinaryTrees;

public class Main {

	static void testTree() {
		Integer[] treeNodes = { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };

		BST<Integer> trees = new BST<>();
		for (int i = 0; i < treeNodes.length; i++) {
			trees.add(treeNodes[i]);
			
		}
		BinaryTrees.print(trees);
//		System.out.println();

//		trees.preorderTraversal();

//		trees.inorderTraversal();
//		trees.postorderTraversal();
		// 7 ,4,9,2,5,8,11,1,3,12
//		trees.levelOrderTraversal();|

//		System.out.println("=====前序遍历=====");
//		trees.preorder(new Visitor<Integer>() {
//
//			@Override
//			boolean visitor(Integer element) {
//				System.out.print(element+"\t");
//				if (element.equals(new Integer(9)) ) {
//					return true;
//				}else {
//					return false;
//				}
//			
//			}
//		});
//		System.out.println();
//		System.out.println("=====中序遍历=====");
//		trees.inorder(new Visitor<Integer>() {
//
//			@Override
//			boolean visitor(Integer element) {
//				System.out.print(element+"\t");
//				if (element == 9) {
//					return true;
//				}else {
//					return false;
//				}
//				
//				
//				
//			}
//		});;
//
//		System.out.println();
//		System.out.println("=====后序遍历=====");
//		trees.postorder(new Visitor<Integer>() {
//
//			@Override
//			boolean visitor(Integer element) {
//				System.out.print(element+"\t");
//				if (element == 9) {
//					return true;
//				}else {
//					return false;
//				}
//				
//				
//			}
//		});;

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

		System.out.println();
		System.out.println("二叉树的高度为 递归的方法" + trees.height());
		
		System.out.println("二叉树的高度为 层序遍历的方法 " + trees.height2());


	}

	
	static void testTreeRemove() {
		Integer[] treeNodes = { 7, 4, 9, 2, 5, 8, 11, 3, 12, 1 };

		BST<Integer> trees = new BST<>();
		for (int i = 0; i < treeNodes.length; i++) {
			trees.add(treeNodes[i]);
			
		}
		System.out.println();
		BinaryTrees.print(trees);
//		trees.remove(1);
//		trees.remove(3);
		trees.remove(4);
		System.out.println();
		BinaryTrees.print(trees);



	}
	
	static void test2() {
		// 创建BST
		Integer data[] = new Integer[] {
				7, 4, 9, 2, 5, 8, 11
		};
		BST<Integer> bst = new BST<>();
		for (int i = 0; i < data.length; i++) {
			bst.add(data[i]);
		}
		// 树状打印
		BinaryTrees.println(bst);
		// 遍历器
		StringBuilder sb = new StringBuilder();
		Visitor<Integer> visitor = new Visitor<Integer>() {
			@Override
			public boolean visitor(Integer element) {
				sb.append(element).append(" ");
				return false;
			}
		};
		// 遍历
		sb.delete(0, sb.length());
		bst.preorder(visitor);
		System.out.println("前序：" + sb );
		Asserts.test(sb.toString().equals("7 4 2 5 9 8 11 "));

		sb.delete(0, sb.length());
		bst.inorder(visitor);
		System.out.println("中序：" + sb );
		Asserts.test(sb.toString().equals("2 4 5 7 8 9 11 "));

		sb.delete(0, sb.length());
		bst.postorder(visitor);
		System.out.println("后序：" + sb );
		Asserts.test(sb.toString().equals("2 5 4 8 11 9 7 "));
	}
	
	
	public static void main(String[] args) {
//		testTreeRemove();
		test2();
	}

}
