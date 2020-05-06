package com.mhz.link;

public class ArrayList<T> extends AbstractList<T> {
	// ����list �ĳ���, ���List ����һ��Ԫ��, �Ƿ��ص�size ����1 , size���Ƿ���Ԫ�صĸ���
	private T[] elements;// ��ǰ���Ԫ�ص�����
	private static final int DEFAULT_CAPACITY = 10;// ��ʼ������

	/**
	 * ÿ����ӵ�ʱ��ȥ��� ��ǰ������ĳ��� �Ƿ���������, �������������, ���Ǿ�ȥ �����µ��ڴ��ַ, ����֮ǰ������, ���Ƶ��µ�������,
	 * ��elementsָ���µ��ڴ��ַ
	 */
	private void ensureCapacity(int capacity) {
		// ���� oldCapacity �����������Ƕ��峣�� DEFAULT_CAPACITY
		// ��������Ƕ���ĳ����Ļ�, ��ô��һ������Ҫȥ���ݵ�ʱ��,
		// oldCapacity ����һ�� �̶���ֵ, ���� newCapacityҲ��һ���̶���ֵ,
		// �Ͳ������� ,��������� elements = newElements;��仰Ҳ�Ǻ���Ҫ��,
		// ��仰 ,�͵����� oldCapacity ��һ���仯�ĳ��� , ����һ���̶��ĳ���
		int oldCapacity = elements.length;
		// ��� �ɵĳ��ȴ��� ��Ҫ�ĳ��� ֱ�ӷ���
		if (oldCapacity >= capacity) {
			return;
		}
		// λ���� , �µĳ����Ǿɵĳ��ȵ�1.5�� oldCapacity >> 1
		// ����oldCapacity*0.5 Ȼ���ڼ���oldCapacity
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		T[] newElements = (T[]) new Object[newCapacity];
		for (int i = 0; i < elements.length; i++) {
			// ��elements�е�Ԫ�� ��ֵ���µ���������
			newElements[i] = elements[i];
		}
		// ���µ������ڴ��ַ, ָ��ɵ�����(elements)
		elements = newElements;
//		System.out.println("size=" + oldCapacity + ", ���ݵ���" + newCapacity);

	}

	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	public ArrayList(int capacity) {
		// �������ĳ�ʼ������ ����10 �� ����Ϊcapacity ������ DEFAULT_CAPACITY Ĭ�ϵĳ�ʼ������
		capacity = capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity;
		elements = (T[]) new Object[capacity];
	}

	/**
	 * ����ĳ��Ԫ�ص�
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
	 * ���Ԫ�ص�ָ����λ��
	 * 
	 * @param index   λ��
	 * @param element Ԫ��
	 */
	public void add(int index, T element) {
		checkIndexForAdd(index); // ����±��Ƿ���ȷ

		// ��ӵ�ʱ��ȥ��� ��ǰ�ĳ��� �Ƿ����㳤��
		ensureCapacity(size + 1);

		// �����ȼ��� , �����Ȳ����ǳ��� ����
		for (int i = size - 1; i >= index; i--) {
			elements[i + 1] = elements[i];

		}
		elements[index] = element;
		size++;
	}

	/**
	 * �õ� ָ��λ�õ�ֵ
	 * 
	 * @param index index �Ǵ�0 ��ʼ��,�������Ǿ���˵�� ������±�
	 * @return
	 */
	public T get(int index) {
		checkIndex(index);

		return elements[index];
	}

	/**
	 * ɾ��Ԫ��
	 * 
	 * @param index index �Ǵ�0��ʼ��, �������Ǿ���˵��������±�
	 * @return ����ɾ����Ԫ��
	 */
	public T remove(int index) {
		checkIndex(index);
		T old = elements[index];
		for (int i = index; i < size - 1; i++) {
			// �������ֵ , ������ǰ��
			elements[i] = elements[i + 1];
		}
		// list�ĳ��� ��һ
		size--;
		return old;

	}

	/**
	 * ����������������
	 */
	public void clear() {
		// ʹ�� ���ͺ�, Ҫע���ڴ�Ĺ���
		for (int i = 0; i < size; i++) {
			elements[i] = null;
		}
		size = 0;
	}

	@Override
	public String toString() {
		/// ��ӡ��ʽΪ: size=5, [99, 88, 77, 66, 55]
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
