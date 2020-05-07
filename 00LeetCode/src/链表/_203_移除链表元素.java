package 链表;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * 
 * 示例: 输入: 1->2->6->3->4->5->6, val = 6 输出: 1->2->3->4->5
 * 
 * https://leetcode-cn.com/problems/remove-linked-list-elements/
 * 
 * @author mahaizhen
 *
 * @date 2020年5月7日
 */
public class _203_移除链表元素 {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node6 = new ListNode(6);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node62 = new ListNode(6);
		node1.next = node2;
		node2.next = node6;
		node6.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node62;
		node62.next = null;
		list(node1);

		ListNode removeNode = removeElements(node1, 6);
		System.out.println();
		list(removeNode);
	}

	/**
	 * 删除链表中 给定值val的所有节点
	 * 
	 * @param head
	 * @param val
	 * @return
	 */
	public static ListNode removeElements(ListNode head, int val) {
		// 虚拟的头节点
		ListNode virtualHead = new ListNode(0);
		// 头节点，关联起来
		virtualHead.next = head;

		// 上一个节点
		ListNode prev = virtualHead;
		// 当前的节点
		ListNode curNode = head;
		while (curNode != null) {
			if (val == curNode.val) {
				// 要删除的节点
				prev.next = curNode.next;
			} else {
				// 如果不是要删除的节点, 就把当前的节点 复制给 上一个节点  ,
				prev = curNode;
			}
			// 然后当前的节点, 往后移
			curNode = curNode.next;

		}

		return virtualHead.next;

	}

	private static void list(ListNode head) {
		ListNode node = head;
		while (node != null) {
			System.out.print(node.val + "\t");
			node = node.next;
		}

	}

}
