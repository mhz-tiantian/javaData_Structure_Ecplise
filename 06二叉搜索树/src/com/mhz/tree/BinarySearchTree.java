package com.mhz.tree;

/**
 * 二叉树 搜索
 * 
 * @author mahaizhen
 *
 * @date 2020年5月14日
 */
public class BinarySearchTree<E> {

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
		Node<E> parent = null;
		while (node != null) {
			int cmp = compare(element, node.element);
			parent = node;
			if (cmp > 0) {
				// ======> 这个方向
				node = node.right;
			} else if (cmp < 0) {
				node = node.right;
			} else {
				return;

			}
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

		return 0;
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

}
