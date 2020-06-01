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
 */
public class BinarySearchTree<E> implements BinaryTreeInfo {

	private Comparator<E> comparator;

	public BinarySearchTree() {
	}

	public BinarySearchTree(Comparator<E> comparator) {
		this.comparator = comparator;
	}

	/**
	 * 
	 * @return 返回是不是一个完全二叉树
	 */
	public boolean isComplete2() {
		// 如果是一个空树, 就返回一个false
		if (root == null)
			return false;
		Queue<Node<E>> queue = new LinkedList<>();
		// 先添加根节点
		queue.offer(root);
		// 下次遍历的时候 所有的节点 都是叶子节点
		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> current = queue.poll();
			Node<E> left = current.left;
			Node<E> right = current.right;
			if (left != null) { // 左不等於空
				queue.offer(left);
			} else if (right != null) {
				// left==null && right!=null
				return false;
			}
			if (right != null) {
				queue.offer(right);
			}

		}

		return true;
	}

	/**
	 * 
	 * @return 返回是不是一个完全二叉树
	 */
	public boolean isComplete() {
		// 如果是一个空树, 就返回一个false
		if (root == null)
			return false;
		Queue<Node<E>> queue = new LinkedList<>();
		// 先添加根节点
		queue.offer(root);
		// 下次遍历的时候 所有的节点 都是叶子节点
		boolean leaf = false;
		while (!queue.isEmpty()) {
			Node<E> current = queue.poll();
			Node<E> left = current.left;
			Node<E> right = current.right;

			// 如果leaf 为true, 当前的节点是叶子节点
			if (leaf && !current.isLeaf()) {
				return false;
			}

			if (current.hasTwoChildren()) {
				queue.offer(left);
				queue.offer(right);
			} else if (left == null && right != null) {
				return false;

			} else {
				leaf = true;
				if (left != null) {
					queue.offer(left);
				}
			}

		}

		return true;
	}

	/**
	 * 返回二叉树的高度
	 * 
	 * @return
	 */
	public int height() {
		return height(root);
	}

	/**
	 * 返回二叉树的高度
	 * 
	 * @return
	 */
	public int height2() {
		if (root == null)
			return 0;
		// 使用层序遍历的方法 来计算层序遍历的方法

		int height = 0;
		Queue<Node<E>> queue = new LinkedList<>();
		// 先把root节点 入队列
		queue.offer(root);
		int levelSize = 1; // 每层节点的数量
		while (!queue.isEmpty()) {
			Node<E> current = queue.poll();
			// 减少一个 size--
			levelSize--;

			Node<E> left = current.left;
			Node<E> right = current.right;
			if (left != null) {
				queue.offer(left);
			}
			if (right != null) {
				queue.offer(right);
			}

			if (levelSize == 0) {
				// 每层遍历完, 把LeverSize的数量重置下
				levelSize = queue.size();
				height++;
			}

		}

		return height;

	}

	/**
	 * 获取某个节点的高度
	 * 
	 * @param node
	 * @return
	 */
	private int height(Node<E> node) {
		if (node == null) {
			return 0;
		}
		int height = 1 + Math.max((height(node.right)), height(node.left));
		return height;

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

	private void postorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) {
			return;
		}
		postorder(node.left, visitor);
		postorder(node.right, visitor);

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
		postorder(root, visitor);
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

	private void inorder(Node<E> node, Visitor<E> visitor) {
		if (node == null || visitor.stop) {
			return;
		}
		inorder(node.left, visitor);
		if (visitor.stop) {
			return;
		}
		visitor.stop = visitor.visitor(node.element);
		inorder(node.right, visitor);

	}

	public void inorder(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		inorder(root, visitor);

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

	private void preorder(Node<E> parent, Visitor<E> visitor) {
		// 递归结束的条件
		if (parent == null || visitor.stop) {
			return;
		}

		visitor.stop = visitor.visitor(parent.element);
		preorder(parent.left, visitor);
		preorder(parent.right, visitor);
	}

	public void preorder(Visitor<E> visitor) {
		// 不用在递归的时候每次都判断了
		if (visitor == null) {
			return;
		}
		preorder(root, visitor);

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

		/**
		 * 是否是 叶子节点(叶子节点就是 左右节点都是null的节点)
		 * 
		 * @return
		 */
		public boolean isLeaf() {
			return right == null && left == null;

		}

		/**
		 * 返回是不是有 二个子节点
		 * 
		 * @return
		 */
		public boolean hasTwoChildren() {
			return right != null && left != null;

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
