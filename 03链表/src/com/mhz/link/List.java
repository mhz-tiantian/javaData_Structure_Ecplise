package com.mhz.link;

public interface List<T> {
	/**
	 * 元素没有找到 的标志位
	 */
	static final int ELEMENT_NOT_FOUND = -1;

	/**
	 * 设置 index 位置的元素
	 * 
	 * @param index   位置
	 * @param element 新的元素
	 * @return 原来index位置的 元素
	 */
	T set(int index, T element);

	/**
	 * 获取index 的位置的元素
	 * 
	 * @param index
	 * @return
	 */
	T get(int index);

	/**
	 * 返回当前集合存放元素的数量
	 * 
	 * @return
	 */
	int getSize();

	/**
	 * 判断当前的集合是不是为空
	 * 
	 * @return
	 */
	boolean isEmpty();

	/**
	 * 
	 * @param element
	 * @return 返回element 在当前集合所在的索引
	 */
	int indexOf(T element);

	/**
	 * 当前集合是不是包括 element 这个元素
	 * 
	 * @param element
	 * @return true 为包括这个元素, false 不包括这个元素
	 */
	boolean contains(T element);

	/**
	 * 添加元素
	 * 
	 * @param element
	 */
	void add(T element);

	/**
	 * 添加元素到指定的位置
	 * 
	 * @param index
	 * @param element
	 */
	void add(int index, T element);

	/**
	 * 删除 index位置的元素 ,
	 * 
	 * @param index 位置 下标
	 * @return 删除的元素 旧的元素
	 */
	T remove(int index);

	/**
	 * 清空当前的集合
	 */
	void clear();
}
