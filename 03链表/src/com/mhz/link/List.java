package com.mhz.link;

public interface List<T> {
	/**
	 * Ԫ��û���ҵ� �ı�־λ
	 */
	static final int ELEMENT_NOT_FOUND = -1;

	/**
	 * ���� index λ�õ�Ԫ��
	 * 
	 * @param index   λ��
	 * @param element �µ�Ԫ��
	 * @return ԭ��indexλ�õ� Ԫ��
	 */
	T set(int index, T element);

	/**
	 * ��ȡindex ��λ�õ�Ԫ��
	 * 
	 * @param index
	 * @return
	 */
	T get(int index);

	/**
	 * ���ص�ǰ���ϴ��Ԫ�ص�����
	 * 
	 * @return
	 */
	int getSize();

	/**
	 * �жϵ�ǰ�ļ����ǲ���Ϊ��
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 
	 * @param element
	 * @return ����element �ڵ�ǰ�������ڵ�����
	 */
	int indexOf(T element);

	/**
	 * ��ǰ�����ǲ��ǰ��� element ���Ԫ��
	 * 
	 * @param element
	 * @return true Ϊ�������Ԫ��, false ���������Ԫ��
	 */
	boolean contains(T element);

	/**
	 * ���Ԫ��
	 * 
	 * @param element
	 */
	void add(T element);

	/**
	 * ���Ԫ�ص�ָ����λ��
	 * 
	 * @param index
	 * @param element
	 */
	void add(int index, T element);

	/**
	 * ɾ�� indexλ�õ�Ԫ�� ,
	 * 
	 * @param index λ�� �±�
	 * @return ɾ����Ԫ�� �ɵ�Ԫ��
	 */
	T remove(int index);

	/**
	 * ��յ�ǰ�ļ���
	 */
	void clear();
}
