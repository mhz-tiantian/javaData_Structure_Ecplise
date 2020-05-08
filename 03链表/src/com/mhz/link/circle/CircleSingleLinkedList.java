package com.mhz.link.circle;

import com.mhz.link.AbstractList;

/**
 * 循环单项链表
 * 
 * @author mahaizhen
 *
 * @date 2020年5月8日
 */
public class CircleSingleLinkedList<T> extends AbstractList<T> {

	Node<T> first;

	static class Node<T> {
		T element;
		Node<T> next;

		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
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
		if (index == 0) {
			Node<T> node = new Node<T>(element, first);
			Node<T> last = size == 0 ? node : node(size - 1);
			first = node;
			// 拿到最后一个节点
			last.next = first;
		} else {
			Node<T> prev = node(index - 1);
			Node<T> addNode = new Node<>(element, prev.next);
			prev.next = addNode;
			if (index == size) { // 添加最后的时候
				addNode.next = first;
			}

		}
		size++;

	}

	@Override
	public T remove(int index) {
		checkIndex(index);
		Node<T> currentNode = null;
		if (index == 0) {
			currentNode = first;
			first = first.next;
		} else {
			Node<T> prev = node(index - 1);
			currentNode = prev.next;
			prev.next = prev.next.next;
		}
		size--;
		return currentNode.element;
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
	}

	/**
	 * 返回 index位置 上面的节点
	 * 
	 * @param index
	 * @return
	 */
	private Node<T> node(int index) {
		checkIndex(index);
		Node<T> returnNode = first;
		// 如果穿入的index =0 , 这个for循环就不走了
		for (int i = 0; i < index; i++) {
			returnNode = returnNode.next;
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
