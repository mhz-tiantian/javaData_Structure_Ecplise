package com.haizhen.tree;

import java.util.Comparator;

/**
 * 二叉树 搜索
 * 
 * @author mahaizhen
 *
 * @date 2020年5月14日
 * 
 *       满二叉树的定义 : 高度为h，由2^h-1个节点构成的二叉树称为满二叉树
 * 
 *       满二叉树的定义: 其实就是最后的节点全部是叶子节点, 然后其他的层 都有二个子节点
 * 
 * 
 * 
 * 
 * 
 *       完全二叉树的定义:
 * 
 *       完全二叉树是由满二叉树而引出来的，若设二叉树的深度为h，除第 h 层外，其它各层 (1～h-1)
 *       的结点数都达到最大个数(即1~h-1层为一个满二叉树)，第 h 层所有的结点都连续集中在最左边，这就是完全二叉树。
 * 
 *       堆一般都是用完全二叉树来实现的。
 * 
 *       完全二叉树的定义:就是在满的基础上, 除去最后一层, 不是满的以外 , 其他的层 也还是必须是满的, 在最后一层的节点, 都连续集中在最左边,
 *       就是完全二叉树
 * 
 *       完全二叉树: 叶子节点只会出现最后2层, 且最后一层的叶子节点 都靠左对齐
 */
@SuppressWarnings("unchecked")
public class BST<E> extends BinaryTree<E> {

	private Comparator<E> comparator;

	public BST() {
	}

	public BST(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	/**
	 * 添加以后的操作
	 * 
	 * @param node 添加的节点
	 */
	protected void afterAdd(Node<E> node) {
	};

	/**
	 * 被删除以后的操作,
	 * 
	 * @param node 真正被删除的节点
	 */
	protected void afterRemove(Node<E> node, Node<E> replaceNode) {
	};

	/**
	 * 添加元素
	 * 
	 * @param element
	 */
	public void add(E element) {
		elementNotNullCheck(element);
		if (root == null) { // 如果添加的是 根节点
			root = createNode(element, null);
			size++;
			afterAdd(root);
			return;
		}

		// 找到父节点 从父节点 开始找到父节点
		Node<E> node = root;
		// 默认的 父节点 从根节点开始查找
		Node<E> parent = root;
		// 记录要往哪个方向来添加
		int cmp = 0;
		while (node != null) {
			cmp = compare(element, node.element);
			// 记录出来父节点
			parent = node;
			if (cmp > 0) {
				// ======> 这个方向
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				// 如果两个值相等的话, 把新的值来覆盖旧的值
				// 当前节点的真实数据 覆盖成新的值
				node.element = element;
				return;

			}
		}
		Node<E> newNode = createNode(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
		// 新添加之后的处理
		afterAdd(newNode);

	}

	/**
	 * 移除元素 删除
	 * 
	 * @param element
	 */
	public void remove(E element) {

		remove(node(element));

	}

	private void remove(Node<E> node) {
		if (node == null) {
			return;
		}
		// 删除的时候 size 要减少
		size--;
		// 度为2 的节点
		if (node.hasTwoChildren()) {
			// 找到前驱/或者后继 的节点
			Node<E> sNode = predecessor(node);
			// 用后继节点的值覆盖 度为2节点的值
			node.element = sNode.element;
			// 删除后继节点
			node = sNode;
		}
		// 删除node 节点即可( node 必然是度为1,或者0的节点)
		Node<E> replaceElement = node.left != null ? node.left : node.right;
		if (replaceElement != null) {
			// 说明 node 是度为1 的节点

			// 更改parent
			replaceElement.parent = node.parent;
			// 更换指向(更换指针)
			// 更换parent的left/right的指向
			if (node.parent == null) {
				// node度为1 , 并且是根节点
				root = replaceElement;
			} else if (node == node.parent.left) {
				// node是父节点的left 节点
				node.parent.left = replaceElement;
			} else {
				// node == node.parent.right
				// node 是父节点的 right节点
				node.parent.right = replaceElement;
			}

			// 删除以后的操作
			afterRemove(node, replaceElement);
		} else if (node.parent == null) {
			// 说明node 为叶子节点(度为0) node.parent == null 说明Node 是根节点
			root = null;
			// 删除以后的操作
			afterRemove(node, null);
		} else {
			// 说明 node 是叶子节点 但是不是跟节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
			// 删除以后的操作
			afterRemove(node, null);

		}

	}

	/**
	 * 
	 * @param e1
	 * @param e2
	 * @return 返回 0 表示 e1与 e2相等, 大于0 ,说明e1 大于 e2 , 小于0 e1小于e2
	 * 
	 */
	private int compare(E e1, E e2) {
		// 比較的方法
		if (comparator != null) {
			return comparator.compare(e1, e2);

		}
		return ((Comparable<E>) e1).compareTo(e2);
	}

	/**
	 * 根据元素的值, 拿到 存放值的节点
	 * 
	 * @param element
	 * @return
	 */
	private Node<E> node(E element) {
		Node<E> node = root;
		while (node != null) {
			int cmp = compare(element, node.element);
			if (cmp == 0) {
				return node;
			} else if (cmp > 0) {
				// element 这个值 比node.element大
				node = node.right;
			} else {
				// element 这个值 比node.element小
				node = node.left;
			}

		}
		return null;
	}

	/**
	 * 是否包含元素
	 * 
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {
		return node(element) != null;
	}

}
