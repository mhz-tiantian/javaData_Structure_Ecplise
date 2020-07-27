package com.haizhen.tree;

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
		// 用于替代删除节点是红色的节点的话, 这里不需要把指针指向替换的节点上, 因为这是已经删除过的, (意思就是这些删除的线都是连接好的)
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
		// 删除的节点是黑色的节点, 在b树中就会产生[下溢]的问题
		// 去找兄弟节点, 去接一个
		// 怎么接 ?
		// 1. 如果兄弟节点可以借(至少含有一个红色的子节点) 才可以接
		// 1.1 进行旋转操作
		// 1.2 旋转之后的中心节点继承parent的颜色
		// 1.3 旋转之后的左右节点染为Black

		// node.isLeftChild() 这句话是为了, 考虑到 这次的下溢 导致父节点的下溢(而父节点的线 我们是没有截断的)
		boolean left = parent.left == null || node.isLeftChild();
		Node<E> sibling = left ? parent.right : parent.left;
		if (left) { // 被删除的节点在左边, 兄弟节点在右边
			// 如果兄弟节点是红色, 把兄弟节点染成黑色, 父节点染成红色, 然后让
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				rotateLeft(parent);
				sibling = parent.right;
			}
			// 到这里兄弟节点必然是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点的子节点没有红色的子节点. 父节点要向下跟兄弟节点合并
				boolean parentBlack=isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}

			}else {
				// 兄弟节点的子节点, 有红色的子节点, 可以借给我们
				// 兄弟节点的左边的子节点是黑色, 兄弟要先旋转
				if (isBlack(sibling.right)) {
					// 先进行旋转, 然后成为下面的两种情况
					rotateRight(sibling);
					// 然后把兄弟节点更新下
					sibling=parent.right;
				}
				// 让兄弟节点, 继承父节点的颜色
				color(sibling, colorOf(parent));
				black(sibling.right);
				black(parent);
				rotateLeft(parent);
			}
			

		} else { // 被删除的节点在右边, 兄弟节点在左边
			// 如果兄弟节点是红色, 把兄弟节点染成黑色, 父节点染成红色, 然后让
			if (isRed(sibling)) {
				black(sibling);
				red(parent);
				rotateRight(parent);
				sibling = parent.left;
			}
			// 到这里兄弟节点必然是黑色
			if (isBlack(sibling.left) && isBlack(sibling.right)) {
				// 兄弟节点的子节点没有红色的子节点. 父节点要向下跟兄弟节点合并
				boolean parentBlack=isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent, null);
				}

			}else {
				// 兄弟节点的子节点, 有红色的子节点, 可以借给我们
				// 兄弟节点的左边的子节点是黑色, 兄弟要先旋转
				if (isBlack(sibling.left)) {
					// 先进行旋转, 然后成为下面的两种情况
					rotateLeft(sibling);
					// 然后把兄弟节点更新下
					sibling=parent.left;
				}
				// 让兄弟节点, 继承父节点的颜色
				color(sibling, colorOf(parent));
				black(sibling.left);
				black(parent);
				rotateRight(parent);
			}

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
