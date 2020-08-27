package com.haizhen.map;

public interface Map<K, V> {

	/**
	 *  返回size
	 * @return
	 */
	int size();

	/**
	 *  是否为空
	 * @return
	 */
	boolean isEmpty();

	/**
	 *  清空数据
	 */
	void clear();

	/**
	 *  放入数据
	 * @param key
	 * @param value
	 * @return
	 */
	V put(K key, V value);

	/**
	 *  获得数据
	 * @param key
	 * @return
	 */
	V get(K key);

	/**
	 *  移除数据 通过key
	 * @param key
	 * @return
	 */
	V remove(K key);

	/**
	 *  映射中是否包含key
	 * @param key
	 * @return
	 */
	boolean containsKey(K key);

	/**
	 *  映射中是否包括 Value
	 * @param value
	 * @return
	 */
	boolean containsValue(V value);

	/**
	 *  遍历的接口
	 * @param visitor
	 */
	void traversal(Visitor<K, V> visitor);

	public static abstract class Visitor<K, V> {
		boolean stop;

		public abstract boolean visit(K key, V value);
	}
}
