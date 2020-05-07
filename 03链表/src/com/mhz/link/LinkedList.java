package com.mhz.link;

@SuppressWarnings("hiding")
public class LinkedList<T> extends AbstractList<T> {

	// 第一个节点 这是没有头节点的 第一个节点 就有元素了
	Node<T> first;

	static class Node<T> {
		T element;
		// 指向下一个元素
		Node<T> next;

		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
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

	@Override
	public void add(int index, T element) {
		// 先检查下 下标 是否正确
		checkIndexForAdd(index);
		if (index == 0) {
			// index =0的时候其实就是 创建first的这个节点 ,
			// 因为当前的first节点其实是空的
			first = new Node<T>(element, first);
		} else {
			// 获得index 的上一个节点
			Node<T> preNode = getNodeIndex(index - 1);
			// 初始化 要添加的节点 上一个节点的next 指向要添加的元素上
			preNode.next = new Node<T>(element, preNode.next);
		}
		size++;

	}

	@Override
	public T remove(int index) {
		Node<T> currentNode = null;
		if (index == 0) {
			currentNode = first;
			// 因为第一个first 节点就存放的元素了, 所有 当index=0 的时候 ,
			// 其实当前的节点就是currentNode 就是first节点
			first = currentNode.next;

		} else {
			// 下面是处理 index>0 的情况
			// 获得上一个节点
			Node<T> preNode = getNodeIndex(index - 1);
			// 获得要删除的节点
			currentNode = preNode.next;
			// 上一个节点的 下一个节点 指向 下一个节点的 下一个节点"
			preNode.next = currentNode.next;
		}

		// 因为节点的个数 减少 , 所以size--
		size--;
		return currentNode.element;
	}

	@Override
	public void clear() {
		size = 0;
		first = null;

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
		Node<T> tempNode = first;
		for (int i = 0; i < index; i++) {
			tempNode = tempNode.next;
		}
		return tempNode;

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
			sb.append(node.element);
			node = node.next;

		}
		sb.append("]");
		System.out.println(sb);
		return sb.toString();
	}

}
