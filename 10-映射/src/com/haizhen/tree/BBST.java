package com.haizhen.tree;

import java.util.Comparator;

public class BBST<E> extends BST<E> {
	public BBST() {
		this(null);
	}
	
	public BBST(Comparator<E> comparator) {
		super(comparator);
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
	protected void rotate(Node<E> root, Node<E> a, Node<E> b, Node<E> c, Node<E> d, Node<E> e, Node<E> f, Node<E> g) {
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
	protected void rotateLeft(Node<E> grand) {
		Node<E> parent = grand.right;
		Node<E> child = parent.left;
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
	protected void rotateRight(Node<E> grand) {
		Node<E> parent = grand.left;
		Node<E> child = parent.right;
		grand.left = child;
		parent.right = grand;

		afterRotate(grand, parent, child);

	}

	// 这个方法主要就是维护旋转之后的父节点的属性
	protected void afterRotate(Node<E> grand, Node<E> parent, Node<E> child) {
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
}
