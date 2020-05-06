package com.mhz.link;

@SuppressWarnings("hiding")
public class LinkedList<T> extends AbstractList<T> {

	// ��һ���ڵ� ����û��ͷ�ڵ�� ��һ���ڵ� ����Ԫ����
	Node<T> first;

	static class Node<T> {
		T element;
		// ָ����һ��Ԫ��
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
		// �ȼ���� �±� �Ƿ���ȷ
		checkIndexForAdd(index);
		if (index == 0) {
			// index =0��ʱ����ʵ���� ����first������ڵ� ,
			// ��Ϊ��ǰ��first�ڵ���ʵ�ǿյ�
			first = new Node<T>(element, first);
		} else {
			// ���index ����һ���ڵ�
			Node<T> preNode = getNodeIndex(index - 1);
			// ��ʼ�� Ҫ��ӵĽڵ� ��һ���ڵ��next ָ��Ҫ��ӵ�Ԫ����
			preNode.next = new Node<T>(element, preNode.next);
		}
		size++;

	}

	@Override
	public T remove(int index) {
		Node<T> currentNode = null;
		if (index == 0) {
			currentNode = first;
			// ��Ϊ��һ��first �ڵ�ʹ�ŵ�Ԫ����, ���� ��index=0 ��ʱ�� ,
			// ��ʵ��ǰ�Ľڵ����currentNode ����first�ڵ�
			first = currentNode.next;

		} else {
			// �����Ǵ��� index>0 �����
			// �����һ���ڵ�
			Node<T> preNode = getNodeIndex(index - 1);
			// ���Ҫɾ���Ľڵ�
			currentNode = preNode.next;
			// ��һ���ڵ�� ��һ���ڵ� ָ�� ��һ���ڵ�� ��һ���ڵ�"
			preNode.next = currentNode.next;
		}

		// ��Ϊ�ڵ�ĸ��� ���� , ����size--
		size--;
		return currentNode.element;
	}

	@Override
	public void clear() {
		size = 0;
		first = null;

	}

	/**
	 * ����indexλ�õ�Ԫ��
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
	 * ����index λ�õĽڵ�
	 * 
	 * @param index
	 * @return
	 */
	private Node<T> getNodeIndex(int index) {

		// �ȼ��index�Ĵ����ֵ �Ƿ���ȷ
		checkIndex(index);
		Node<T> tempNode = first;
		for (int i = 0; i < index; i++) {
			tempNode = tempNode.next;
		}
		return tempNode;

	}

	@Override
	public String toString() {
		/// ��ӡ��ʽΪ: size=5, [99, 88, 77, 66, 55]
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
