package ����;
/**
 *  �ݹ� ��ӡ�������
 * @author Administrator
 *
 */
public class Test {

	public static void main(String[] args) {
		test(4);
	}
	
	private   static void test(int n) {
		if (n>2) {
			test(n-1);
		}
		System.out.println("n===="+n);
		
	}
	
	

}
