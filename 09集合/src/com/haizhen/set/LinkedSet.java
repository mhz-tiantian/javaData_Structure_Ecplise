package com.haizhen.set;

import com.haizhen.list.LinkedList;
import com.haizhen.list.List;

public class LinkedSet<E> implements Set<E> {
	private List<E> list = new LinkedList<>();

	@Override
	public int size() {
		return list.getSize();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public void clear() {
		list.clear();

	}

	@Override
	public boolean contains(E element) {
		return list.contains(element);
	}

	@Override
	public void add(E element) {
		int index = list.indexOf(element);
		if (index != List.ELEMENT_NOT_FOUND) {
			list.set(index, element);
		} else {
			list.add(element);
		}

	}

	@Override
	public void remove(E element) {
		int index = list.indexOf(element);
		if (index != list.ELEMENT_NOT_FOUND) {
			list.remove(index);
		}
	}

	@Override
	public void traversal(Visitor<E> visitor) {
		if (visitor == null) {
			return;
		}
		int size = list.getSize();
		for (int i = 0; i < size; i++) {
			if (visitor.visit(list.get(i))) {
				return;
			}

		}

	}

}
