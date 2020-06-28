package com.haizhen.list;

/**
 * 抽象类, 用于封装 子类 一样的方法
 * 
 * @author mahaizhen
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
	 * 判断 某个元素 在集合中是否包括
	 * 
	 * @return 找到元素返回true 没有找到返回false
	 */
	@Override
	public boolean contains(T element) {
		// 找到元素
		return indexOf(element) != ELEMENT_NOT_FOUND;

	}

	/**
	 * 数组越界异常 , 对于下标的检查
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
	 * 添加的时候去检查下标
	 * 
	 * @param index
	 */
	protected void checkIndexForAdd(int index) {
		if (index < 0 || index > size) {
			outofBounds(index);
		}
	}

}
