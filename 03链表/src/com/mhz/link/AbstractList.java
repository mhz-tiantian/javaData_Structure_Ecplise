package com.mhz.link;

/**
 * ������, ���ڷ�װ ���� һ���ķ���
 * 
 * @author Administrator
 *
 * @param <T>
 */
public abstract class AbstractList<T> implements List<T> {

	protected int size;

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	
	@Override
	public void add(T element) {
		add(size, element);
	}
	
	
	/**
	 * �ж� ĳ��Ԫ�� �ڼ������Ƿ����
	 * 
	 * @return �ҵ�Ԫ�ط���true û���ҵ�����false
	 */
	@Override
	public boolean contains(T element) {
		// �ҵ�Ԫ��
		return indexOf(element) != ELEMENT_NOT_FOUND;

	}

	/**
	 * ����Խ���쳣 , �����±�ļ��
	 * 
	 * @param index
	 */
	protected void outofBounds(int index) {

		throw new RuntimeException("Index:" + index + ", Size:" + size);
	}

	protected void checkIndex(int index) {
		if (index < 0 || index >= size) {
			outofBounds(index);
		}
	}

	/**
	 * ��ӵ�ʱ��ȥ����±�
	 * 
	 * @param index
	 */
	protected void checkIndexForAdd(int index) {
		if (index < 0 || index > size) {
			outofBounds(index);
		}
	}

}
