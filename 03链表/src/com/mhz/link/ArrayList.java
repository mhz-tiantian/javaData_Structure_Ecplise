package com.mhz.link;

public class ArrayList<T> extends AbstractList<T> {
	// 返回list 的长度, 如果List 中有一个元素, 那返回的size 就是1 , size就是返回元素的个数
	private T[] elements;// 当前存放元素的数组
	private static final int DEFAULT_CAPACITY = 10;// 初始化容量

	/**
	 * 每次添加的时候去检查 当前的数组的长度 是否满足需求, 如果不满足需求, 我们就去 开辟新的内存地址, 并把之前的数组, 复制到新的数组中,
	 * 让elements指向新的内存地址
	 */
	private void ensureCapacity(int capacity) {
		// 这里 oldCapacity 还不能是我们定义常量 DEFAULT_CAPACITY
		// 如果是我们定义的常量的话, 那么下一次再需要去扩容的时候,
		// oldCapacity 就是一个 固定的值, 所以 newCapacity也是一个固定的值,
		// 就不会扩容 ,方法的最后 elements = newElements;这句话也是很重要的,
		// 这句话 ,就导致了 oldCapacity 是一个变化的长度 , 不是一个固定的长度
		int oldCapacity = elements.length;
		// 如果 旧的长度大于 需要的长度 直接返回
		if (oldCapacity >= capacity) {
			return;
		}
		// 位运算 , 新的长度是旧的长度的1.5倍 oldCapacity >> 1
		// 就是oldCapacity*0.5 然后在加上oldCapacity
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		T[] newElements = (T[]) new Object[newCapacity];
		for (int i = 0; i < elements.length; i++) {
			// 把elements中的元素 赋值到新的数组里面
			newElements[i] = elements[i];
		}
		// 把新的数组内存地址, 指向旧的数组(elements)
		elements = newElements;
//		System.out.println("size=" + oldCapacity + ", 扩容到了" + newCapacity);

	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayList(int capacity) {
		// 如果传入的初始化容量 大于10 则 容量为capacity 否则是 DEFAULT_CAPACITY 默认的初始化容量
		capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
		elements = (T[]) new Object[capacity];
	}

	/**
	 * 返回某个元素的
	 * 
	 * @param element
	 * @return
	 */
	public int indexOf(T element) {
		if (element == null) {
			for (int i = 0; i < size; i++) {
				if (elements[i] == null) {
					return i;
				}

			}
		} else {
			for (int i = 0; i < size; i++) {
				if (element.equals(elements[i])) {
					return i;
				}

			}
		}

		return ELEMENT_NOT_FOUND;

	}

	/**
	 * 添加元素到指定的位置
	 * 
	 * @param index   位置
	 * @param element 元素
	 */
	public void add(int index, T element) {
		checkIndexForAdd(index); // 检查下标是否正确

		// 添加的时候去检查 当前的长度 是否满足长度
		ensureCapacity(size + 1);

		// 我们先假设 , 我们先不考虑长度 问题
		for (int i = size - 1; i >= index; i--) {
			elements[i + 1] = elements[i];

		}
		elements[index] = element;
		size++;
	}

	/**
	 * 得到 指定位置的值
	 * 
	 * @param index index 是从0 开始的,就是我们经常说的 数组的下标
	 * @return
	 */
	public T get(int index) {
		checkIndex(index);

		return elements[index];
	}

	/**
	 * 删除元素
	 * 
	 * @param index index 是从0开始的, 就是我们经常说的数组的下标
	 * @return 返回删除的元素
	 */
	public T remove(int index) {
		checkIndex(index);
		T old = elements[index];
		for (int i = index; i < size - 1; i++) {
			// 把整体的值 , 整体往前移
			elements[i] = elements[i + 1];
		}
		// list的长度 减一
		size--;
		return old;

	}

	/**
	 * 清空数组里面的内容
	 */
	public void clear() {
		// 使用 泛型后, 要注意内存的管理
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public String toString() {
		/// 打印形式为: size=5, [99, 88, 77, 66, 55]
		StringBuffer sb = new StringBuffer();
		sb.append("size=");
		sb.append(size);
		sb.append(",[");
		for (int i = 0; i < size; i++) {
			if (i != 0) {
				sb.append(",");
			}
			sb.append(elements[i]);

		}
		sb.append("]");
		System.out.println(sb);
		return sb.toString();
	}

	@Override
	public T set(int index, T element) {
		checkIndex(index);
		T oldElement = elements[index];
		elements[index] = element;
		return oldElement;
	}

}
