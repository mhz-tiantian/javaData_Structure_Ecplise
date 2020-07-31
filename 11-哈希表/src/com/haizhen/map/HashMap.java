package com.haizhen.map;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import com.haizhen.model.Key;
import com.haizhen.printer.BinaryTreeInfo;
import com.haizhen.printer.BinaryTrees;

@SuppressWarnings("unchecked")
public class HashMap<K, V> implements Map<K, V> {

	private static final boolean BLACK = true;
	private static final boolean RED = false;
	private int size;
	// 红黑树根节点的table数组
	private Node<K, V>[] table;
	private static final int DEFAULT_CAPACITY = 1 << 4;

	// 转填因子
	private static final float DEFAULT_LOAD_FACTOR = 0.75F;

	public HashMap() {
		// 1<<4 2的4次方
		table = new Node[DEFAULT_CAPACITY];
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
		if (size == 0) {
			return;
		}
		int tableLength = table.length;
		for (int i = 0; i < tableLength; i++) {
			table[i] = null;
		}
		size = 0;
	}

	@Override
	public V put(K key, V value) {
		resize();
		int index = index(key);
		// 取出index位置的红黑树的根节点
		Node<K, V> root = table[index];
		if (root == null) {
			root = createNode(key, value, null);
			// 放到桶的index位置
			table[index] = root;
			size++;
			fixAfterPut(root);
			return null;
		}
		// 根节点不为空, hash值冲突的时候
		// 添加新的节点到红黑树上面

		// 找到父节点 从父节点 开始找到父节点
		Node<K, V> node = root;
		// 默认的 父节点 从根节点开始查找
		Node<K, V> parent = root;
		// 记录要往哪个方向来添加
		int cmp = 0;
		K k1 = key;
		int h1 = hash(key);
		Node<K, V> result = null;
		boolean searched = false;
		do {
			// 记录出来父节点
			parent = node;
			K k2 = node.key;
			int h2 = node.hash;
			if (h1 > h2) {
				cmp = 1;
			} else if (h1 < h2) {
				cmp = -1;
			} // 哈希值相等的时候
			else if (Objects.equals(k1, k2)) {
				cmp = 0;
			} else if (k1 != null && k2 != null && (k1.getClass() == k2.getClass()) && k1 instanceof Comparable
					&& (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
			} else if (searched) { // searched ==true
				cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
			} else { // 没有扫描过
				// 哈希值相等， 不具备可比较性。 不equals
				// 先去扫描下 然后再根据内存地址确定左右
				if ((node.left != null && (result = node(node.left, k1)) != null)
						|| (node.right != null && (result = node(node.right, k1)) != null)) {
					node = result;
					cmp = 0;
				} else {
					// 不存在这个key
					searched = true;
					cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
				}
			}

			if (cmp > 0) {
				// ======> 这个方向
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			} else {
				V oldValue = value;
				node.value = value;
				node.key = key;
				node.hash = h1;
				return oldValue;
			}

		} while (node != null);
		Node<K, V> newNode = createNode(key, value, parent);
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		size++;
		// 新添加之后的处理
		fixAfterPut(newNode);

		return null;
	}

	protected Node<K, V> createNode(K key, V value, Node<K, V> parent) {
		return new Node<>(key, value, parent);
	}

	@Override
	public V get(K key) {
		Node<K, V> node = node(key);
		return node != null ? node.value : null;
	}

	@Override
	public V remove(K key) {
		return remove(node(key));
	}

	@Override
	public boolean containsKey(K key) {
		return node(key) != null;
	}

	@Override
	public boolean containsValue(V value) {
		if (size == 0) {
			return false;
		}
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				continue;
			}
			queue.offer(table[i]);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (Objects.equals(value, node.value)) {
					return true;
				}
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
			}

		}
		return false;
	}

	@Override
	public void traversal(Visitor<K, V> visitor) {
		if (size == 0 || visitor == null) {
			return;
		}
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < table.length; i++) {
			if (table[i] == null) {
				continue;
			}
			queue.offer(table[i]);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (visitor.visit(node.key, node.value)) {
					return;
				}
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}

			}

		}

	}

	public void print() {
		if (size == 0) {
			return;
		}
		for (int i = 0; i < table.length; i++) {
			final Node<K, V> root = table[i];
			System.out.println("【index ==== " + i + "】");
			BinaryTrees.print(new BinaryTreeInfo() {

				@Override
				public Object string(Object node) {
					return node;
				}

				@Override
				public Object root() {
					return root;
				}

				@Override
				public Object right(Object node) {
					return ((Node<K, V>) node).right;
				}

				@Override
				public Object left(Object node) {
					return ((Node<K, V>) node).left;
				}
			});
			System.out.println("============================");

		}

	}

	private V remove(Node<K, V> node) {
		if (node == null) {
			return null;
		}

		Node<K, V> willNode = node;
		V oldValue = node.value;
		// 删除的时候 size 要减少
		size--;
		// 度为2 的节点
		if (node.hasTwoChildren()) {
			// 找到前驱/或者后继 的节点
			Node<K, V> sNode = successor(node);
			// 用后继节点的值覆盖 度为2节点的值
			node.value = sNode.value;
			node.key = sNode.key;
			// 哈希值也得覆盖掉
			node.hash = sNode.hash;
			// 删除后继节点
			node = sNode;
		}
		// 删除node 节点即可( node 必然是度为1,或者0的节点)
		Node<K, V> replaceNode = node.left != null ? node.left : node.right;
		int index = index(node);
		if (replaceNode != null) {
			// 说明 node 是度为1 的节点
			// 更改parent
			replaceNode.parent = node.parent;
			// 更换指向(更换指针)
			// 更换parent的left/right的指向
			if (node.parent == null) {
				// node度为1 , 并且是根节点
				table[index] = replaceNode;
			} else if (node == node.parent.left) {
				// node是父节点的left 节点
				node.parent.left = replaceNode;
			} else {
				// node == node.parent.right
				// node 是父节点的 right节点
				node.parent.right = replaceNode;
			}

			// 删除以后的操作
			fixAfterRemove(replaceNode);
		} else if (node.parent == null) {
			// 说明node 为叶子节点(度为0) node.parent == null 说明Node 是根节点
			table[index] = null;
			// 删除以后的操作
//			afterRemove(node);
		} else {
			// 说明 node 是叶子节点 但是 不是跟节点
			if (node == node.parent.left) {
				node.parent.left = null;
			} else {
				node.parent.right = null;
			}
			// 删除以后的操作
			fixAfterRemove(node);

		}

		afterRemove(willNode, node);

		return oldValue;

	}

	protected void afterRemove(Node<K, V> willNode, Node<K, V> removeNode) {

	}

	private Node<K, V> node(K key) {
		Node<K, V> root = table[index(key)];
		return root == null ? null : node(root, key);
	}

	private Node<K, V> node(Node<K, V> node, K k1) {
		// 先拿到key 所在位置的索引
		int h1 = hash(k1);
		Node<K, V> result = null;
		int cmp = 0;
		while (node != null) {
			int h2 = node.hash;
			K k2 = node.key;
			// 先比较哈希值, 然后去确定往左往右
			if (h1 > h2) {
				node = node.right;
			} else if (h1 < h2) {
				node = node.left;
			} else if (Objects.equals(k1, k2)) {
				// 如果k1 与k2 equals相等的话 ， 就直接返回
				return node;
			} else if (k1 != null && k2 != null && k1.getClass() == k2.getClass() && k1 instanceof Comparable
					&& ((cmp = ((Comparable) k1).compareTo(k2)) != 0)) {
				node = cmp > 0 ? node.right : node.left;
			}
			// 哈希值相等, 但是不具备可比较性
			// 并且equals 是不相等的
			else if ((node.right != null) && (result = node(node.right, k1)) != null) {
				return result;
			} else {
				node = node.left;
			}
//			} else if (node.left != null && ((result = node(node.left, k1)) != null)) {
//				return result;
//			} else {
//				return null;
//			}

		}
		return null;
	}

	private void resize() {
		// 转填因子 <=0.75
		if (size / table.length <= DEFAULT_LOAD_FACTOR) {
			return;
		}
		Node<K, V>[] oldTable = table;
		table = new Node[table.length << 1];
		Queue<Node<K, V>> queue = new LinkedList<>();
		for (int i = 0; i < oldTable.length; i++) {
			if (oldTable[i] == null) {
				continue;
			}
			queue.offer(oldTable[i]);
			while (!queue.isEmpty()) {
				Node<K, V> node = queue.poll();
				if (node.left != null) {
					queue.offer(node.left);
				}
				if (node.right != null) {
					queue.offer(node.right);
				}
				moveNode(node);

			}
		}

	}

	private void moveNode(Node<K, V> newNode) {
		// 重置
		newNode.parent = null;
		newNode.left = null;
		newNode.right = null;
		newNode.color = RED;
		int index = index(newNode);
		Node<K, V> root = table[index];
		if (root == null) {
			root = newNode;
			table[index] = root;
			fixAfterPut(root);
			return;
		}
		// 默认的 父节点 从根节点开始查找
		Node<K, V> node = root;
		Node<K, V> parent = root;
		// 记录要往哪个方向来添加
		int cmp = 0;
		K k1 = newNode.key;
		int h1 = newNode.hash;
		do {
			// 记录出来父节点
			parent = node;
			K k2 = node.key;
			int h2 = node.hash;
			if (h1 > h2) {
				cmp = 1;
			} else if (h1 < h2) {
				cmp = -1;
			} // 哈希值相等的时候
			else if (k1 != null && k2 != null && (k1.getClass() == k2.getClass()) && k1 instanceof Comparable
					&& (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
			} else { // 没有扫描过
				cmp = System.identityHashCode(k1) - System.identityHashCode(k2);
			}

			if (cmp > 0) {
				// ======> 这个方向
				node = node.right;
			} else if (cmp < 0) {
				node = node.left;
			}

		} while (node != null);
		newNode.parent = parent;
		if (cmp > 0) {
			parent.right = newNode;
		} else {
			parent.left = newNode;
		}
		// 新添加之后的处理
		fixAfterPut(newNode);
	}

	/**
	 * 求出哈希值
	 * 
	 * @param key
	 * @return
	 */
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
		int hash = key.hashCode();
		return hash ^ (hash >>> 16);
	}

	/**
	 * 拿到key 节点所在桶的index
	 */
	private int index(K key) {
		return hash(key) & (table.length - 1);

	}

	/**
	 * 拿到当前节点所在桶的index
	 * 
	 * @param node
	 * @return
	 */
	private int index(Node<K, V> node) {
		return node.hash & (table.length - 1);
	}

	private void fixAfterPut(Node<K, V> node) {
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
		Node<K, V> grand = red(parent.parent);
		// 叔父节点是红色的时候[B树节点上溢]
		if (isRed(uncle)) {
			black(parent);
			black(uncle);
			fixAfterPut(grand);
			return;
		}
		// 叔父节点不是红色的

		if (parent.isLeftChild()) {// L
			if (node.isLeftChild()) {// LL
				black(parent);

			} else {// LR
				black(node);
				// 父节点本来就是红色的, 所以不用染色
				rotateLeft(parent);
			}
			rotateRight(grand);

		} else {// R
			if (node.isLeftChild()) {// RL
				black(node);
				rotateRight(parent);

			} else {// RR
				black(parent);
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
				s = s.left;
			}
			return s;
		}
		while (node.parent != null && node == node.parent.right) {
			node = node.parent;
		}
		return node.parent;

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
			this.table[index(grand)] = parent;
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

	protected void fixAfterRemove(Node<K, V> node) {
		// 删除的是红色的节点, 不做任何的处理
//		if (isRed(node)) {
//			return;
//		}
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
					fixAfterRemove(parent);
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
					fixAfterRemove(parent);
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

	protected static class Node<K, V> {
		int hash;
		K key;
		V value;
		boolean color = RED;
		Node<K, V> left;// 左边节点
		Node<K, V> right; // 右边节点
		Node<K, V> parent; // 父节点

		@Override
		public String toString() {
			return "Node_" + key + "_" + value;
		}

		public Node(K key, V value, Node<K, V> parent) {
			this.key = key;
			this.value = value;
			this.parent = parent;
			this.hash = key == null ? 0 : key.hashCode();
			this.hash = hash ^ (hash >>> 16);
		}

		protected boolean isRightChild() {
			return parent != null && this == parent.right;
		}

		/**
		 * 判断当前的节点 是在父节点的左子树
		 * 
		 * @return
		 */
		protected boolean isLeftChild() {
			return parent != null && this == parent.left;
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
			if (isRightChild()) {
				return parent.left;
			}
			if (isLeftChild()) {
				return parent.right;
			}
			return null;
		}

	}

}
