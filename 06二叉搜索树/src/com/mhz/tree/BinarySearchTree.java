package com.mhz.tree;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import com.mhz.tree.printer.BinaryTreeInfo;

/**
 * 二叉树 搜索
 * 
 * @author mahaizhen
 *
 * @date 2020年5月14日
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

	private Comparator<E> comparator;

	public BinarySearchTree() {
	}

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	// 二叉树的根节点
	private Node<E> root;

	private int size;

	/**
	 * 
	 * @return 返回元素的数量
	 */
	public int size() {
		return size;
	}

	/**
	 * 是否为空树
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 清楚树
	 */
	public void clear() {

	}

	public void levelOrder(Visitor<E> visitor) {
		if (root == null || visitor == null) {
			return;
		}
		// 生成一个队列 并且放回root
		Queue<Node<E>> queue = new LinkedList<>();
		// 往队列里,添加元素
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 从队头获取元素, 并且更新队列
			Node<E> current = queue.poll();
			boolean stop = visitor.visitor(current.element);
			// stop 如果为true 表示的是遍历结束, 直接返回
			if (stop) {
				return;
			}
			Node<E> left = current.left;
			if (left != null) {
				queue.add(left);
			}
			Node<E> right = current.right;
			if (right != null) {
				queue.add(right);
			}
		}
	}

	/**
	 * 层序遍历
	 */
	private void levelOrderTraversal() {
		if (root == null) {
			return;
		}
		// 生成一个队列 并且放回root
		Queue<Node<E>> queue = new LinkedList<>();
		// 往队列里,添加元素
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 从队头获取元素, 并且更新队列
			Node<E> current = queue.poll();
			System.out.println(current.element);
			Node<E> left = current.left;
			if (left != null) {
				queue.add(left);
			}
			Node<E> right = current.right;
			if (right != null) {
				queue.add(right);
			}
		}

	}

	/**
	 * 后序遍历
	 */
	public void postorderTraversal() {
		postorderTraversal(root);
	}

	private void postorderTraversal(Node<E> node) {
		if (node == null) {
			return;
		}
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		System.out.println(node.element);

	}

	private void postorderTraversal(Node<E> node, Visitor<E> visitor) {
		System.out.println("postorder" + visitor.stop);
		if (node == null || visitor.stop) {
			return;
		}
		postorderTraversal(node.left);
		postorderTraversal(node.right);
		
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visitor(node.element);

	}

	/**
	 * 后序遍历,
	 * 
	 * @param visitor 使用者
	 */
	public void postorder(Visitor<E> visitor) {
		if (visitor == null) {
			return;

		}
		postorderTraversal(root, visitor);
	}

	/**
	 * 中序遍历
	 */
	public void inorderTraversal() {
		inorderTraversal(root);

	}

	private void inorderTraversal(Node<E> node) {
		if (node == null) {
			return;
		}
		inorderTraversal(node.left);
		System.out.println(node.element);
		inorderTraversal(node.right);

	}

	private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
		System.out.println("inorder" + visitor.stop);
		if (node == null || visitor.stop) {
			return;
		}
		inorderTraversal(node.left);
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visitor(node.element);
		inorderTraversal(node.right);

	}

	public void inorder(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		inorderTraversal(root, visitor);

	}

	/**
	 * 先序遍历
	 */
	public void preorderTraversal() {
		preorderTraversal(root);

	}

	/**
	 * 前序遍历
	 * 
	 * @param parent
	 */
	private void preorderTraversal(Node<E> parent) {
		// 递归结束的条件
		if (parent == null) {
			return;
		}
		System.out.println(parent.element);
		preorderTraversal(parent.left);
		preorderTraversal(parent.right);
	}

	private void preorderTraversal(Node<E> parent, Visitor<E> visitor) {
		System.out.println("preorder" + visitor.stop);
		// 递归结束的条件
		if (parent == null || visitor.stop) {
			return;
		}

		visitor.stop = visitor.visitor(parent.element);
		preorderTraversal(parent.left);
		preorderTraversal(parent.right);
	}

	public void preorder(Visitor<E> visitor) {
		// 不用在递归的时候每次都判断了
		if (visitor == null) {
			return;
		}
		preorderTraversal(root, visitor);

	}

	/**
	 * 添加元素
	 * 
	 * @param element
	 */
	public void add(E element) {
		elementNotNullCheck(element);
		if (root == null) { // 如果添加的是 根节点
			root = new Node<>(element, null);
			size++;
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
		Node<E> newNode = new Node<>(element, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
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
	 * 移除元素
	 * 
	 * @param element
	 */
	public void remove(E element) {

	}

	/**
	 * 是否包含元素
	 * 
	 * @param element
	 * @return
	 */
	public boolean contains(E element) {

		return false;
	}

	/**
	 * 检查传入的元素是否为null的判断
	 * 
	 * @param element
	 */

	private void elementNotNullCheck(E element) {
		if (element == null) {
			// IllegalArgumentException 非法差数异常
			throw new IllegalArgumentException(" element must not be null");
		}

	}

	// Visitor 访问者
	public static abstract class Visitor<E> {
		// 遍历是否结束的标记
		boolean stop;

		abstract boolean visitor(E element);
	}

	static class Node<E> {
		Node<E> left;// 左边节点
		Node<E> right; // 右边节点
		E element; // 真正放置的元素
		Node<E> parent; // 父节点

		public Node(E element, Node<E> parent) {
			this.element = element;
			this.parent = parent;
		}

	}

	@Override
	public Object root() {
		return root;
	}

	@Override
	public Object left(Object node) {
		Node<E> current = (Node<E>) node;
		return current.left;
	}

	@Override
	public Object right(Object node) {
		Node<E> current = (Node<E>) node;
		return current.right;
	}

	@Override
	public Object string(Object node) {
		Node<E> current = (Node<E>) node;
		if (current.parent == null) {
			return current.element + "_p(" + null + ")";
		}
		return current.element + "_p(" + current.parent.element + ")";
	}

}
