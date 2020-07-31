package com.haizhen.map;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeMap<K, V> implements Map<K, V> {

	private static final boolean RED = false;
	private static final boolean BLACK = true;

	private Comparator<K> comparator;

	public TreeMap() {
		this(null);
	}

	public TreeMap(Comparator<K> comparator) {
		this.comparator = comparator;
	}

	// 二叉树的根节点
	protected Node<K, V> root;
	protected int size;

	private Node<K, V> color(Node<K, V> node, boolean color) {
		if (node == null) {
			return node;
		}
		node.color = color;
		return node;
	}

	private Node<K, V> red(Node<K, V> node) {
		return color(node, RED);
	}

	private Node<K, V> black(Node<K, V> node) {
		return color(node, BLACK);
	}

	private boolean colorOf(Node<K, V> node) {
		return node == null ? BLACK : node.color;
	}

	private boolean isBlack(Node<K, V> node) {
		return colorOf(node) == BLACK;
	}

	private boolean isRed(Node<K, V> node) {
		return colorOf(node) == RED;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	@Override
	public V put(K key, V value) {
		KeyNotNullCheck(key);
		if (root == null) { // 如果添加的是 根节点
			root = new Node<>(key, value, null);
			size++;
			afterPut(root);
			return null;
		}

		// 找到父节点 从父节点 开始找到父节点
		Node<K, V> node = root;
		// 默认的 父节点 从根节点开始查找
		Node<K, V> parent = root;
		// 记录要往哪个方向来添加
		int cmp = 0;
		while (node != null) {
			cmp = compare(key, node.key);
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
				V oldValue = value;
				node.value = value;
				node.key = key;
				return oldValue;

			}
		}
		Node<K, V> newNode = new Node<>(key, value, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
		// 新添加之后的处理
		afterPut(newNode);

		return null;
	}

	private int compare(K key1, K key2) {
		if (comparator != null) {
			return comparator.compare(key1, key2);
		}
		return ((Comparable<K>) key1).compareTo(key2);
	}

	@Override
	public V get(K key) {
		Node<K, V> node = node(key);
		return node == null ? null : node.value;
	}

	@Override
	public V remove(K key) {
		return remove(node(key));
	}

	private V remove(Node<K, V> node) {
		if (node == null) {
			return null;
		}
		// 删除的时候 size 要减少
		size--;
		// 度为2 的节点
		if (node.hasTwoChildren()) {
			// 找到前驱/或者后继 的节点
			Node<K, V> sNode = predecessor(node);
			// 用后继节点的值覆盖 度为2节点的值
			node.value = sNode.value;
			node.key = sNode.key;
			// 删除后继节点
			node = sNode;
		}
		// 删除node 节点即可( node 必然是度为1,或者0的节点)
		Node<K, V> replaceElement = node.left != null ? node.left : node.right;
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
			afterRemove(replaceElement);
		} else if (node.parent == null) {
			// 说明node 为叶子节点(度为0) node.parent == null 说明Node 是根节点
			root = null;
			// 删除以后的操作
			afterRemove(node);
		} else {
			// 说明 node 是叶子节点 但是不是跟节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
			// 删除以后的操作
			afterRemove(node);

		}

		return null;

	}

	/**
	 * 是否包括 key
	 */
	@Override
	public boolean containsKey(K key) {
		return node(key) != null;
	}

	/**
	 * 判断是否包括Value 值
	 */
	@Override
	public boolean containsValue(V value) {
		if (root == null) {
			return false;
		}
		Queue<Node<K, V>> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			Node<K, V> node = queue.poll();
			if (valEquals(value, node.value)) {
				return true;
			}
			Node<K, V> left = node.left;
			if (left != null) {
				queue.offer(left);
			}
			Node<K, V> right = node.right;
			if (right != null) {
				queue.offer(right);
			}

		}

		return false;
	}

	private boolean valEquals(V v1, V v2) {
		return v1 == null ? v2 == null : v1.equals(v2);
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		if (visitor == null) {
			return;
		}

		inOrder(visitor, root);

	}

	private void inOrder(Visitor<K, V> visitor, Node<K, V> node) {
		if (node == null) {
			return;
		}
		inOrder(visitor, node.left);
		if (visitor.stop) {
			return;
		}
		visitor.visit(node.key, node.value);
		inOrder(visitor, node.right);
	}

	/**
	 * 因为删除的节点, 只能是叶子节点(在内存中真正销毁内存的 )
	 */
	protected void afterRemove(Node<K, V> node) {
		// 用于替代删除节点是红色的节点的话, 这里不需要把指针指向替换的节点上, 因为这是已经删除过的, (意思就是这些删除的线都是连接好的)
		if (isRed(node)) {
			// 把当前的节点 染成黑色, 单独分离成一个单独的节点
			black(node);
			return;

		}

		Node<K, V> parent = node.parent;
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
		Node<K, V> sibling = left ? parent.right : parent.left;
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
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent);
				}

			} else {
				// 兄弟节点的子节点, 有红色的子节点, 可以借给我们
				// 兄弟节点的左边的子节点是黑色, 兄弟要先旋转
				if (isBlack(sibling.right)) {
					// 先进行旋转, 然后成为下面的两种情况
					rotateRight(sibling);
					// 然后把兄弟节点更新下
					sibling = parent.right;
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
				boolean parentBlack = isBlack(parent);
				black(parent);
				red(sibling);
				if (parentBlack) {
					afterRemove(parent);
				}

			} else {
				// 兄弟节点的子节点, 有红色的子节点, 可以借给我们
				// 兄弟节点的左边的子节点是黑色, 兄弟要先旋转
				if (isBlack(sibling.left)) {
					// 先进行旋转, 然后成为下面的两种情况
					rotateLeft(sibling);
					// 然后把兄弟节点更新下
					sibling = parent.left;
				}
				// 让兄弟节点, 继承父节点的颜色
				color(sibling, colorOf(parent));
				black(sibling.left);
				black(parent);
				rotateRight(parent);
			}

		}

	}

	private void afterPut(Node<K, V> node) {
		Node<K, V> parent = node.parent;
		// 添加的是根节点 或者是上溢到了 根节点
		if (parent == null) {
			black(node);
			return;
		}
		if (isBlack(parent)) {
			return;
		}

		Node<K, V> uncle = parent.sibling();
		Node<K, V> grand = parent.parent;
		// 叔父节点是红色的时候[B树节点上溢]
		if (isRed(uncle)) {
			black(parent);
			black(uncle);
			afterPut(red(grand));
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

	}

	/**
	 * @param node 当前节点
	 * @return 返回当前节点 的前驱节点
	 */

	protected Node<K, V> predecessor(Node<K, V> node) {
		if (node == null) {
			return node;
		}
		Node<K, V> p = node.left;
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

	protected Node<K, V> successor(Node<K, V> node) {
		if (node == null) {
			return node;
		}
		Node<K, V> s = node.right;
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

	/**
	 * 根据元素的值, 拿到 存放值的节点
	 * 
	 * @param element
	 * @return
	 */
	private Node<K, V> node(K key) {
		Node<K, V> node = root;
		while (node != null) {
			int cmp = compare(key, node.key);
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
	 * 让d成为这个子树的根节点
	 * 
	 * @param root
	 * @param a
	 * @param b
	 * @param c
	 * @param d
	 * @param e
	 * @param f
	 * @param g
	 */
	protected void rotate(Node<K, V> root, Node<K, V> a, Node<K, V> b, Node<K, V> c, Node<K, V> d, Node<K, V> e,
			Node<K, V> f, Node<K, V> g) {
		d.parent = root.parent;
		if (root.isLeftChild()) {
			root.parent.left = d;
		} else if (root.isRightChild()) {
			root.parent.right = d;
		} else {
			// 这里需要注意下, 不是当前行参中的root
			this.root = d;
		}
//		a-b-c
		b.left = a;
		if (a != null) {
			a.parent = b;
		}
		b.right = c;
		if (c != null) {
			c.parent = b;
		}

		// e-f-g

		f.left = e;
		if (e != null) {
			e.parent = f;
		}
		f.right = g;
		if (g != null) {
			g.parent = f;
		}

		// b-d-f
		d.left = b;
		d.right = f;
		b.parent = d;
		f.parent = d;

	}

	/**
	 * 左旋转
	 * 
	 * @param node
	 */
	protected void rotateLeft(Node<K, V> grand) {
		Node<K, V> parent = grand.right;
		Node<K, V> child = parent.left;
		grand.right = child;
		parent.left = grand;
		afterRotate(grand, parent, child);
	}

	/**
	 * 右旋转
	 * 
	 * 
	 * @param node
	 */
	protected void rotateRight(Node<K, V> grand) {
		Node<K, V> parent = grand.left;
		Node<K, V> child = parent.right;
		grand.left = child;
		parent.right = grand;

		afterRotate(grand, parent, child);

	}

	// 这个方法主要就是维护旋转之后的父节点的属性
	protected void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
		// 让 parent 称为这个子树的根节点
		if (grand.isLeftChild()) {
			grand.parent.left = parent;
		} else if (grand.isRightChild()) {
			grand.parent.right = parent;
		} else {
			root = parent;
		}
		// 更新parent 的父节点
		parent.parent = grand.parent;

		// 更新grand的父节点
		grand.parent = parent;

		// 更新child 的父节点
		if (child != null) {
			child.parent = grand;
		}

	}

	private void KeyNotNullCheck(K key) {
		if (key == null) {
			// IllegalArgumentException 非法差数异常
			throw new IllegalArgumentException(" key must not be null");
		}

	}

	private static class Node<K, V> {
		K key;
		V value;
		boolean color = RED;
		Node<K, V> left;// 左边节点
		Node<K, V> right; // 右边节点
		Node<K, V> parent; // 父节点

		public Node(K key, V value, Node<K, V> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
		}

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
		 * 返回兄弟节点
		 * 
		 * @return
		 */

		public Node<K, V> sibling() {
			if (isLeftChild()) {
				return parent.right;
			}
			if (isRightChild()) {
				return parent.left;
			}
			return null;
		}

	}

}
