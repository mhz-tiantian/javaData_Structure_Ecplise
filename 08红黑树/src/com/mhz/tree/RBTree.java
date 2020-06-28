package com.mhz.tree;

import java.util.Comparator;

/**
 * 红黑树 也是一种自平衡的二叉搜索树也叫(平衡二叉B树)
 * 
 * @author mahaizhen
 * 
 *         1. 节点是Red或者Black 2. 根节点是Black 3. 叶子节点(外部节点, 空节点)都是black 4.
 *         Red节点的子节点都是black Red节点的parent节点都是black 从根节点到叶子节点的所有路径上不能有2个连续的Red节点
 *         5. 从任一节点到任子节点的所有路径都包括相同数目的black节点
 * 
 * 
 * 
 *
 * @date 2020年6月23日
 */
public class RBTree<E> extends BBST<E> {

	private static final boolean RED = false;
	private static final boolean BLACK = true;

	public RBTree() {
		this(null);
	}

	public RBTree(Comparator<E> comparator) {
		super(comparator);
	}

	private Node<E> color(Node<E> node, boolean color) {
		if (node == null) {
			return node;
		}
		((RBNode<E>) node).color = color;
		return node;
	}

	private Node<E> red(Node<E> node) {
		return color(node, RED);
	}

	private Node<E> black(Node<E> node) {
		return color(node, BLACK);
	}

	private boolean colorOf(Node<E> node) {
		return node == null ? BLACK : ((RBNode<E>) node).color;
	}

	private boolean isBlack(Node<E> node) {
		return colorOf(node) == BLACK;
	}

	private boolean isRed(Node<E> node) {
		return colorOf(node) == RED;
	}

	@Override
	protected Node<E> createNode(E element, Node<E> parent) {
		return new RBNode<E>(element, parent);
	}

	/**
	 * 因为删除的节点, 只能是叶子节点(在内存中真正销毁内存的 )
	 */
	@Override
	protected void afterRemove(Node<E> node, Node<E> replaceNode) {
		// 删除的是红色的节点, 不做任何的处理
		if (isRed(node)) {
			return;
		}
		// 用于替代删除节点是红色的节点的话,
		if (isRed(replaceNode)) {
			// 把当前的节点 染成黑色, 单独分离成一个单独的节点
			black(node);
			return;

		}

		Node<E> parent = node.parent;
		// 删除的节点, 不是红色的节点
		if (parent == null) {
			// 删除的节点是根节点
			return;
		}

	}

	/**
	 * 在B树中 在添加 必定添加在叶子节点 1. 4阶B树中所有的节点的个数 1<=x<=3
	 */
	@Override
	protected void afterAdd(Node<E> node) {
		Node<E> parent = node.parent;
		// 添加的是根节点 或者是上溢到了 根节点
		if (parent == null) {
			black(node);
			return;
		}
		if (isBlack(parent)) {
			return;
		}

		Node<E> uncle = parent.sibling();
		Node<E> grand = parent.parent;
		// 叔父节点是红色的时候[B树节点上溢]
		if (isRed(uncle)) {
			black(parent);
			black(uncle);
			afterAdd(red(grand));
			return;
		}
		// 叔父节点不是红色的

		if (parent.isLeftChild()) {// L
			red(grand);
			if (node.isLeftChild()) {// LL
//				red(grand);
				black(parent);
//				rotateRight(grand);

			} else {// LR
//				red(grand);
				black(node);
				// 父节点本来就是红色的, 所以不用染色
//				red(parent);
				rotateLeft(parent);
//				rotateRight(grand);

			}
			rotateRight(grand);

		} else {// R
			red(grand);
			if (node.isLeftChild()) {// RL
				black(node);
//				red(grand);
				rotateRight(parent);
//				rotateLeft(grand);

			} else {// RR
				black(parent);
//				red(grand);
//				rotateLeft(grand);
			}
			rotateLeft(grand);

		}

	};

	private static class RBNode<E> extends Node<E> {

		// 默认的节点颜色是红色的节点 , 后果通过添加, 或者删除来更新节点的颜色
		boolean color = RED;

		public RBNode(E element, Node<E> parent) {
			super(element, parent);
		}

		@Override
		public String toString() {
			String str = "";
			if (color == RED) {
				str = "RED_";
			}

			return str + element.toString();
		}

	}

}
