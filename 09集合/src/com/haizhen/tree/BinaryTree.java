package com.haizhen.tree;

import java.util.LinkedList;
import java.util.Queue;


@SuppressWarnings("unchecked")
public class BinaryTree<E>  {

	// 二叉树的根节点
	protected Node<E> root;
	protected int size;

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
		root = null;
		size = 0;

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
			if (leaf && !current.isLeaf()) {
				return false;

			}
			if (left != null) { // 左不等於空
				queue.offer(left);
			} else if (right != null) {
				// left==null && right!=null
				return false;
			}
			if (right != null) { // 右边不为空
				queue.offer(right);
			} else {
				// left!=null &&right==null
				// left ==null && right==null
				leaf = true;
			}

		}

		return true;
	}

	/**
	 * 
	 * @return 返回是不是一个完全二叉树
	 * 
	 * 
	 *         完全二叉树的特点, 1.叶子
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
	 * @param node 当前节点
	 * @return 返回当前节点 的前驱节点
	 */

	protected Node<E> predecessor(Node<E> node) {
		if (node == null) {
			return node;
		}
		Node<E> p = node.left;
		// 如果左子树, 不是null, 从左子树里面找到 最右的那个节点, 前驱节点, 在左子树中
		if (p != null) {
			while (p.right != null) {
				p = p.right;
			}
			return p;
		}

		// 说明 左子树为null, 那就去找父节点, 找到当前节点在父节点中, 的右子树里面, 结束
		while (node.parent != null && node == node.parent.left) {
			node = node.parent;
		}
		// node.parent==null;
		// node== node.parent.right
		return node.parent;
	}

	/**
	 * 返回后继节点
	 * 
	 * @return
	 */

	protected Node<E> successor(Node<E> node) {
		if (node == null) {
			return node;
		}
		Node<E> s = node.right;
		if (s != null) {
			while (s.left != null) {
				s = node.left;
			}
			return s;
		}
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		// node.parent==null;
		// node== node.parent.left
		return node.parent;

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
	 * 创建节点
	 * 
	 * @param element
	 * @param parent
	 * @return
	 */
	protected Node<E> createNode(E element, Node<E> parent) {

		return new Node<E>(element, parent);
	}

	/**
	 * 检查传入的元素是否为null的判断
	 * 
	 * @param element
	 */

	protected void elementNotNullCheck(E element) {
		if (element == null) {
			// IllegalArgumentException 非法差数异常
			throw new IllegalArgumentException(" element must not be null");
		}

	}


	protected static class Node<E> {
		Node<E> left;// 左边节点
		Node<E> right; // 右边节点
		E element; // 真正放置的元素
		Node<E> parent; // 父节点

		protected boolean isRightChild() {
			if (parent != null && this == parent.right) {
				return true;
			}
			return false;
		}

		/**
		 * 判断当前的节点 是在父节点的左子树
		 * 
		 * @return
		 */
		protected boolean isLeftChild() {
			if (parent != null && this == parent.left) {
				return true;
			}
			return false;
		}

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
		

		/**
		 *  返回兄弟节点
		 * @return
		 */
		
		public Node<E> sibling(){
			if (isLeftChild()) {
				return parent.right;
			}
			if (isRightChild()) {
				return parent.left;
			}
			return  null;
		}


		@Override
		public String toString() {
			Node<E> current = this;
			if (current.parent == null) {
				return current.element + "_p(" + null + ")";
			}
			return current.element + "_p(" + current.parent.element + ")";
		}

	}

	// Visitor 访问者
	public static abstract class Visitor<E> {
		// 遍历是否结束的标记
		boolean stop;

		public abstract boolean visitor(E element);

	}

}
