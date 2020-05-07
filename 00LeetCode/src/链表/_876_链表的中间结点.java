package 链表;

/**
 * 
 * @author mahaizhen
 *
 * @date 2020年5月7日
 * 
 *       给定一个带有头结点 head 的非空单链表，返回链表的中间结点。 如果有两个中间结点，则返回第二个中间结点。
 * 
 *       输入：[1,2,3,4,5] 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 
 *       [1,2,3,4,5,6] 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 
 *       https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * 
 */
public class _876_链表的中间结点 {

	public static void main(String[] args) {

		ListNode head = new ListNode(0);
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		head.next = node1;
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = null;
		list(node1);
		ListNode middleNode = middleNode(node1);
		System.out.println();
		list(middleNode);

	}

	public static ListNode middleNode(ListNode head) {
		ListNode middleNode = null;
		int count = 0;
		ListNode currentNode = head;
		while (currentNode != null) {
			count++;
			currentNode = currentNode.next;
		}
		int middleCount = (count >> 1) + 1;

		middleNode = head;
		for (int i = 1; i < middleCount; i++) {
			middleNode = middleNode.next;

		}
		return middleNode;

	}

	private static void list(ListNode head) {
		ListNode node = head;
		while (node != null) {
			System.out.print(node.val + "\t");
			node = node.next;
		}

	}

}
