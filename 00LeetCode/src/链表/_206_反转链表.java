package 链表;

/**
 * 反转链表
 * 
 * @author Administrator 反转一个单链表。
 * 
 *         示例:
 * 
 *         输入: 1->2->3->4->5->NULL 输出: 5->4->3->2->1->NULL 进阶:
 *         你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *         链接：https://leetcode-cn.com/problems/reverse-linked-list
 */
public class _206_反转链表 {

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
	 * 非递归的方式来反转链表
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode reverseList2(ListNode head) {
		ListNode newHead = null;
		while (head != null) {
			// 首先把Temp指针  指向 第二个节点
			ListNode temp = head.next;
			//newHead 第一次的newHead就是个NUll;
			// 把head.next复制给 newHead
			head.next = newHead;
			// 把head复制给newHead  用于下次循环
			newHead = head;
			// 再把当前的temp节点复制给head节点 用于下次循环
			head = temp;

		}

		return newHead;

	}

	/**
	 * 反转链表 递归的方式来反转
	 * 
	 * @param head
	 * @return 思路1. reverseList 这个方法的作用就是 反转链表 假设:我们现在有这样一个链表:5->4->3->2->1->NULL
	 * 
	 *         现在head为5 , 如果我们调用完, 我们的方法后 结果: 1->2->3->4->5->NULL 我们想 如果
	 *         我们reverseList(head.next)( 4->3->2->1->NULL)去调用我们的方法的话 应该是这样的
	 *         1->2->3->4->null
	 * 
	 * 
	 */
	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		// 递归去调用了
		ListNode newHead = reverseList(head.next);
		// 利用头节点拿到 反转后最后的节点, 然后把最后的节点的next节点
		// 重新执行头节点, 就可以了,
		head.next.next = head;
		// 然后把头节点的next节点指向next 就可以完成反转了
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
