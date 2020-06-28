package com.mhz.tree;

import java.util.Comparator;

import com.mhz.tree.BinaryTree.Node;

/**
 * AVL树 定义: 是最先发明的自平衡二叉搜索树, 在AVL树种任何节点的两个子树的高度最大差别为1 所以它也被称为高度平衡树
 * 增加和删除可能需要通过一次或者多次 树旋转来重新平衡这个树 特点:1,. 本身是一颗二叉搜索树 2. 带有平衡条件,
 * 每个节点的左右子树的高度之差的绝对值(平衡因子)最多为1 也就是说 AVL树, 本质上是带了 平衡功能的二叉搜索树
 * 
 * 
 * 平衡的概念:当节点的数量是固定的时候,左右子树的高度越接近,这颗二叉树越平衡(高度越低)
 * 
 * 为什么有AVL树:
 * 
 * 二叉搜索树的时间复杂度跟树的高度有关, 如果在添加的时候是按照从小到大的去添加元素的话, 那么树的高度, 就跟元素的个数一样了,
 * 那么他的时间复杂度,就不好了 比如:2->4->5->8->9->11 那么这个二叉搜索树其实只有右子树, 其实这时候的二叉搜索树就退化成了 链表
 * 所以有了自平衡的二叉搜索树(AVL树)
 * 
 * 
 * @author mahaizhen
 *
 * @date 2020年6月4日
 */
public class AVLTree<E> extends BBST<E> {

	public AVLTree() {
		this(null);
	}

	public AVLTree(Comparator<E> comparator) {
		super(comparator);
	}

	@Override
	protected void afterRemove(Node<E> node, Node<E> replaceNode) {
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {
				// 更新高度
				updateHeight(node);
			} else {
				// 不平衡就去恢复平衡
				rebalance(node);
			}

		}
	}

	@Override
	protected void afterAdd(Node<E> node) {
		// 添加以后 看看有没有失衡的节点, 如果有恢复平衡
		while ((node = node.parent) != null) {
			if (isBalanced(node)) {
				// 如果是平衡的 就更新高度
				updateHeight(node);
			} else {
				// 恢复平衡
				rebalance(node);
				break;
			}

		}

	}

	/**
	 * 恢复平衡
	 * 
	 * @param grand 高度最低的不平衡的节点 g
	 */
	private void rebalance2(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>) grand).tallerChild();
		Node<E> node = ((AVLNode<E>) parent).tallerChild();
		if (parent.isLeftChild()) {
			if (node.isLeftChild()) { // LL
				rotateRight(grand);
			} else { // LR
				// 先对parent 进行左旋转 让 node成为 parent 这个子树的根节点,
				rotateLeft(parent);
				// 再对grand 进行右旋转, 让node 成为grand 这个子树的根节点
				rotateRight(grand);
			}

		} else {
			if (node.isLeftChild()) {// RL
				rotateRight(parent);
				rotateLeft(grand);

			} else {// RR
				rotateLeft(grand);
			}
		}

	}

	/**
	 * 恢复平衡
	 * 
	 * @param grand 高度最低的不平衡的节点 g
	 */
	private void rebalance(Node<E> grand) {
		Node<E> parent = ((AVLNode<E>) grand).tallerChild();
		Node<E> node = ((AVLNode<E>) parent).tallerChild();
		if (parent.isLeftChild()) {
			if (node.isLeftChild()) { // LL
				rotate(grand, node.left, node, node.right, parent, parent.right, grand, grand.right);

			} else { // LR
				rotate(grand, parent.left, parent, node.left, node, node.right, grand, grand.right);
			}

		} else {
			if (node.isLeftChild()) {// RL
				rotate(grand, grand.left, grand, node.left, node, node.right, parent, parent.right);

			} else {// RR
				rotate(grand, grand.left, grand, parent.left, parent, node.left, node, node.right);

			}
		}
	}

	@Override
	protected void rotate(Node<E> root, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
		super.rotate(root, a, b, c, d, e, f, g);
		// 更新高度
		updateHeight(b);
		updateHeight(f);
		updateHeight(d);

	}

	/**
	 * 更新高度
	 */
	@Override
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
		super.afterRotate(grand, parent, child);
		updateHeight(grand);
		updateHeight(parent);
	}

	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new AVLNode<E>(element, parent);
	}

	/**
	 * 判断当前的节点, 是否平衡
	 * 
	 * @param node
	 * @return
	 */
	private boolean isBalanced(Node<E> node) {
		// 平衡因子的绝对值 小于1 就是平衡的
		return Math.abs(((AVLNode<E>) node).balanceFactor()) <= 1;
	}

	/**
	 * 更新节点的高度
	 * 
	 * @param node
	 */
	private void updateHeight(Node<E> node) {
		((AVLNode<E>) node).updateHeight();
	}

	private static class AVLNode<E> extends Node<E> {
		// 我们来规定下 叶子节点的高度为1
		int height = 1;

		public AVLNode(E element, Node<E> parent) {
			super(element, parent);
		}

		/**
		 * 返回左右子树中 , 高度最高的那个子树
		 * 
		 * @return
		 */
		public Node<E> tallerChild() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;
			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			if (leftHeight > rightHeight) {
				return left;
			}
			if (leftHeight < rightHeight) {
				return right;
			}
			return isLeftChild() ? left : right;

		}

		/**
		 * 计算当前节点的平衡 因子
		 * 
		 * @return
		 */
		private int balanceFactor() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;

			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;

			return leftHeight - rightHeight;

		}

		/**
		 * 更新自己的高度
		 */
		private void updateHeight() {
			int leftHeight = left == null ? 0 : ((AVLNode<E>) left).height;

			int rightHeight = right == null ? 0 : ((AVLNode<E>) right).height;
			height = 1 + Math.max(leftHeight, rightHeight);

		}

		@Override
		public String toString() {
			AVLNode<E> current = this;
			if (current.parent == null) {
				return "(" + height + "h)" + current.element + "_p(" + null + ")";
			}
			return "(" + height + "h)" + current.element + "_p(" + current.parent.element + ")";
		}
	}
}
