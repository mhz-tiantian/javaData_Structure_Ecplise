package com.mhz.array;

/**
 * ����ArrayList ֻ��int ֵ
 * 
 * @author Administrator
 *
 */
public class TestArrayList {

	public static void main(String[] args) {

		ArrayList arrayList = new ArrayList(2);
		arrayList.add(29);
		arrayList.add(89);
		arrayList.add(67);
		arrayList.add(23);
		arrayList.add(43);

		String resultString = arrayList.toString();
		System.out.print(resultString);
		arrayList.add(2, 100);
		resultString = arrayList.toString();
		System.out.print(resultString);
		int removeElement = arrayList.remove(5);
		System.out.print("ɾ����Ԫ��Ϊ" + removeElement);
		
		 resultString = arrayList.toString();
		
//		arrayList.clear();
//		resultString = arrayList.toString();
//		System.out.print("��պ�ļ���==="+resultString);

	}

}
