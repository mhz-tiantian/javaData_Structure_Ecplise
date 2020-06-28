package com.haizhen.set;

import com.haizhen.tree.BinaryTree;
import com.haizhen.tree.RBTree;

public class TreeSet<E> implements Set<E> {
	
	private RBTree<E> tree=new RBTree<>();

	@Override
	public int size() {
		return tree.size();
	}

	@Override
	public boolean isEmpty() {
		return tree.isEmpty();
	}

	@Override
	public void clear() {
		tree.clear();

	}

	@Override
	public boolean contains(E element) {
		return tree.contains(element);
	}

	@Override
	public void add(E element) {
		tree.add(element);

	}

	@Override
	public void remove(E element) {
		tree.remove(element);

	}

	@Override
	public void traversal(Visitor<E> visitor) {
		tree.inorder(new BinaryTree.Visitor<E>() {
			@Override
			public boolean visitor(E element) {
				return visitor.visit(element);
			}
		});

	}

}
