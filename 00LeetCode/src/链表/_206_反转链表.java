package ����;

/**
 * ��ת����
 * 
 * @author Administrator ��תһ��������
 * 
 *         ʾ��:
 * 
 *         ����: 1->2->3->4->5->NULL ���: 5->4->3->2->1->NULL ����:
 *         ����Ե�����ݹ�ط�ת�������ܷ������ַ����������⣿
 *         ���ӣ�https://leetcode-cn.com/problems/reverse-linked-list
 */
public class _206_��ת���� {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = null;
		list(node1);
		ListNode newHead = reverseList2(node1);
		list(newHead);
	}

	/**
	 * �ǵݹ�ķ�ʽ����ת����
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode reverseList2(ListNode head) {
		ListNode newHead = null;
		while (head != null) {
			// ���Ȱ�Tempָ��  ָ�� �ڶ����ڵ�
			ListNode temp = head.next;
			//newHead ��һ�ε�newHead���Ǹ�NUll;
			// ��head.next���Ƹ� newHead
			head.next = newHead;
			// ��head���Ƹ�newHead  �����´�ѭ��
			newHead = head;
			// �ٰѵ�ǰ��temp�ڵ㸴�Ƹ�head�ڵ� �����´�ѭ��
			head = temp;

		}

		return newHead;

	}

	/**
	 * ��ת���� �ݹ�ķ�ʽ����ת
	 * 
	 * @param head
	 * @return ˼·1. reverseList ������������þ��� ��ת���� ����:��������������һ������:5->4->3->2->1->NULL
	 * 
	 *         ����headΪ5 , ������ǵ�����, ���ǵķ����� ���: 1->2->3->4->5->NULL ������ ���
	 *         ����reverseList(head.next)( 4->3->2->1->NULL)ȥ�������ǵķ����Ļ� Ӧ����������
	 *         1->2->3->4->null
	 * 
	 * 
	 */
	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		// �ݹ�ȥ������
		ListNode newHead = reverseList(head.next);
		// ����ͷ�ڵ��õ� ��ת�����Ľڵ�, Ȼ������Ľڵ��next�ڵ�
		// ����ִ��ͷ�ڵ�, �Ϳ�����,
		head.next.next = head;
		// Ȼ���ͷ�ڵ��next�ڵ�ָ��next �Ϳ�����ɷ�ת��
		head.next = null;
		return newHead;
	}

	private static void list(ListNode head) {
		ListNode node = head;
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}

	}

}
