package com.haizhen.set;

public interface Set<E> {
	/**
	 *  返回集合的size
	 * @return
	 */
	int size();

	/**
	 *  集合是否为空
	 * @return
	 */
	boolean isEmpty();

	/**
	 *  清空集合的数据
	 */
	void clear();

	/**
	 *  判断集合中是否包括某个元素
	 * @param element
	 * @return
	 */
	boolean contains(E element);

	/**
	 *  添加元素
	 * @param element
	 */
	void add(E element);

	/**
	 *  移除元素
	 * @param element
	 */
	void remove(E element);

	/**
	 * 
	 * @param visitor
	 */
	void traversal(Visitor<E> visitor);

	public static abstract class Visitor<E> {
		boolean stop;

		public abstract boolean visit(E element);
	}

}
