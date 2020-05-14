package com.mhz.link;

import com.mhz.base.AbstractList;

/**
 * 循环双向链表
 * 
 * @author mahaizhen
 *
 * @date 2020年5月8日
 */
public class CircleDoubleLinkedList<T> extends AbstractList<T> {

	// 第一个节点
	Node<T> first;

	// 最后的节点
	Node<T> last;

	static class Node<T> {
		// 元素的值
		T element;
		// 下一个节点
		Node<T> next;
		// 上一个节点
		Node<T> prev;

		public Node(Node<T> prev, T element, Node<T> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			sb.append(prev.element);
			sb.append("-");
			sb.append(element);
			sb.append("_");
			// 因为是循环链表 所有 next 不会为空
			sb.append(next.element);
			return sb.toString();
		}

	}

	@Override
	public T set(int index, T element) {
		checkIndex(index);
		Node<T> node = node(index);
		T oldelement = node.element;
		// 设置新的 元素到 当前的节点上去
		node.element = element;
		return oldelement;
	}

	@Override
	public T get(int index) {
		return node(index).element;
	}

	@Override
	public int indexOf(T element) {
		Node<T> node = first;
		if (null == element) {
			for (int i = 0; i < size; i++) {
				if (null == node.element) {
					return i;
				}
				node = node.next;
			}

		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(node.element)) {
					return i;

				}
				node = node.next;

			}

		}
		// 如果没有找到,返回没有找到 标识
		return ELEMENT_NOT_FOUND;
	}

	@Override
	public void add(int index, T element) {
		checkIndexForAdd(index);

		if (index == size) {// 往最后一个几点添加元素
			Node<T> oldLast = last;
			last = new Node<T>(oldLast, element, first);
			if (size == 0) {
				first = last;
				first.next = first;
				first.prev = first;
			} else {
				oldLast.next = last;
				first.prev = last;
			}

		} else {
			Node<T> next = node(index);
			Node<T> prev = next.prev;
			Node<T> addNode = new Node<>(prev, element, next);
			prev.next = addNode;
			next.prev = addNode;
			if (index == 0) { // 添加第一个节点的时候   让first节点指向新添加的节点
				first = addNode;
			}

		}
		size++;

	}

	@Override
	public T remove(int index) {
		checkIndex(index);
		Node<T> currentNode = first;
		// 只有一个元素的时候删除节点
		if (size == 1) {
			first = null;
			last = null;
		} else {
			currentNode = node(index);
			Node<T> prev = currentNode.prev;
			Node<T> next = currentNode.next;
			prev.next = next;
			next.prev = prev;
			// 这也是很重要的 因为CircleDoubleLinkedList中, 如果不添加这句话的话, first还是指向原来的 节点
			if (currentNode == first) { // 删除第一个的时候
				first = next;
			}
			// 下面 这个同上面一个原理
			if (currentNode == last) {// 删除最后一个的时候
				last = prev;
			}
		}
		size--;
		return currentNode.element;
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;
	}

	/**
	 * 返回 index位置 上面的节点
	 * 
	 * @param index
	 * @return
	 */
	private Node<T> node(int index) {
		checkIndex(index);
		Node<T> returnNode = null;
		// 説明在链表的前半段
		if (index < (size >> 1)) {
			returnNode = first;
			// 如果穿入的index =0 , 这个for循环就不走了
			for (int i = 0; i < index; i++) {
				returnNode = returnNode.next;
			}

		} else {
			// 说明在链表的后半段
			returnNode = last;
			// 如果穿入的index =0 , 这个for循环就不走了
			for (int i = size - 1; i > index; i--) {
				returnNode = returnNode.prev;
			}
		}

		return returnNode;

	}

	@Override
	public String toString() {
		/// 打印形式为: size=5, [99, 88, 77, 66, 55]
		StringBuffer sb = new StringBuffer();
		sb.append("size=");
		sb.append(size);
		sb.append(",[");
		Node<T> node = first;
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(node);
			sb.append("    ");
			node = node.next;

		}
		sb.append("]");
		System.out.println(sb);
		return sb.toString();
	}

}
