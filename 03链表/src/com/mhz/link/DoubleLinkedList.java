package com.mhz.link;

import com.mhz.link.AbstractList;

/**
 * 双向链表 没有头节点的
 * 
 * @author mahaizhen
 *
 * @date 2020年5月8日
 */
@SuppressWarnings("unchecked")
public class DoubleLinkedList<T> extends AbstractList<T> {

	// 第一个节点 这是没有头节点的 第一个节点 就有元素了
	Node<T> first;
	// 最后一个节点
	Node<T> last;

	static class Node<T> {
		T element;
		// 指向下一个元素
		Node<T> next;
		Node<T> prev;

		public Node(Node<T> prev, T element, Node<T> next) {
			this.prev = prev;
			this.element = element;
			this.next = next;
		}

		@Override
		public String toString() {
			StringBuffer sb = new StringBuffer();
			if (prev == null) {
				sb.append("null");
			} else {
				sb.append(prev.element);
			}
			sb.append("_");
			sb.append(element);
			sb.append("_");
			if (next == null) {
				sb.append("null");
			} else {
				sb.append(next.element);
			}

			return sb.toString();
		}

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
				if (node.element.equals(element)) {
					return i;
				}
				node = node.next;
			}
		}

		return ELEMENT_NOT_FOUND;
	}

	@SuppressWarnings("unused")
	@Override
	public void add(int index, T element) {
		// 先检查下 下标 是否正确
		checkIndexForAdd(index);
		// 如果是双向链接 我们不需要去查找 上一个节点了, 只需要查找到当前的(index)的节点就可以了
		if (index == size) {
			// 添加最后一个节点的时候
			Node<T> oldlast = last;
			last = new Node<T>(oldlast, element, null);
			if (oldlast == null) {
				// 如果size ==0 的时候 last 跟first指针同时 指向新添加的节点
				first = last;
			} else {
				oldlast.next = last;
			}

		} else {
			Node<T> next = getNodeIndex(index);
			Node<T> prev = next.prev;
			Node<T> currentNode = new Node<T>(prev, element, next);
			if (prev == null) { // 相当于 index ==0 的时候
				// 让first 节点 指向当前的节点啊
				first = currentNode;
			} else {
				prev.next = currentNode;
			}

			next.prev = currentNode;
		}
		size++;

	}

	@Override
	public T remove(int index) {
		Node<T> removeNode = getNodeIndex(index);
		Node<T> prev = removeNode.prev;
		Node<T> next = removeNode.next;
		if (prev == null) {// index ==0 的时候
			first = next;
		} else {
			prev.next = next;
		}
		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
		}

		// 因为节点的个数 减少 , 所以size--
		size--;
		return removeNode.element;
	}

	@Override
	public void clear() {
		size = 0;
		first = null;
		last = null;

	}

	/**
	 * 返回index位置的元素
	 */
	@Override
	public T get(int index) {
		return getNodeIndex(index).element;
	}

	@Override
	public T set(int index, T element) {
		Node<T> oldNode = getNodeIndex(index);
		T elemenT2 = oldNode.element;
		oldNode.element = element;
		return elemenT2;
	}

	/**
	 * 返回index 位置的节点
	 * 
	 * @param index
	 * @return
	 */
	private Node<T> getNodeIndex(int index) {
		// 先检查index的传入的值 是否正确
		checkIndex(index);
		// 如果当前 传入的index 是比size的一半小的话, 就从头节点开始查找
		if (index < (size >> 1)) {
			Node<T> tempNode = first;
			for (int i = 0; i < index; i++) {
				tempNode = tempNode.next;
			}
			return tempNode;

		} else {
			// 比size的一半大的话, 就从未节点开始查找
			Node<T> tempNode = last;
			for (int i = size - 1; i > index; i--) {
				tempNode = tempNode.prev;
			}
			return tempNode;

		}

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
