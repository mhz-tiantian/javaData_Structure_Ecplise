package 链表;

/**
 * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点， 你将只被给定要求被删除的节点。 现有一个链表 -- head
 * = [4,5,1,9]，它可以表示为:
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
 *
 */
public class _237_删除链表中的节点 {
	/**
	 * 输入: head = [4,5,1,9], node = 5 输出: [4,1,9] 解释:
	 * 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
	 * 
	 */

	/**
	 * 刪除链表上的节点
	 * 
	 * @param node
	 */
	public static void deleteNode(ListNode node) {
		// 思路分析: 因为我们拿不到 头节点, 只能拿到当前的节点
		// 还有当前节点的下一个节点 next节点
		ListNode next = node.next;
		// 把当前节点的next节点val复制给要删除的节点 
		node.val=next.val;
		// 把删除的节点的next指针指向,  删除节点的next节点的next 
		node.next=next.next;
		//这两句话的意思 其实就是把删除节点next节点数组, 复制到删除的节点上, 
		// 其实没有真实的删除 当前的节点, 而是把要删除的下一个节点给删除了

	}

	public static void main(String[] args) {
		ListNode node1 = new ListNode(4);
		ListNode node5=new ListNode(5);
		ListNode node6=new ListNode(1);
		ListNode node9=new ListNode(9);
		node1.next =node5;
		node1.next.next = node6;
		node1.next.next.next =node9;
		
		list(node1);

		deleteNode(node6);
		
		list(node1);

	}

	private static void list(ListNode head) {
		ListNode node = head;
		while (node != null) {
			System.out.println(node.val);
			node = node.next;
		}

	}
}
