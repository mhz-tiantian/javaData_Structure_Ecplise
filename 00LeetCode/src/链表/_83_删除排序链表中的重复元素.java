package 链表;

/**
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 * 
 * https://leetcode-cn.com/problems/middle-of-the-linked-list/
 * 
 * @author mahaizhen
 *
 * @date 2020年5月7日
 * 
 * 
 *
 *       示例 1: 输入: 1->1->2 输出: 1->2 示例 2:
 * 
 *       输入: 1->1->2->3->3 输出: 1->2->3
 */
public class _83_删除排序链表中的重复元素 {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		node1.setId(1);
		ListNode node2 = new ListNode(1);
		node2.setId(2);
		ListNode node6 = new ListNode(1);
		node6.setId(3);
		ListNode node7 = new ListNode(1);
		node7.setId(4);
		ListNode node3 = new ListNode(2);
		node3.setId(5);
		ListNode node4 = new ListNode(3);
		node4.setId(6);
		ListNode node5 = new ListNode(3);
		node5.setId(7);
		node1.next = node2;
		node2.next = node6;
		node6.next = node7;
		node7.next = node3;
		node3.next = node4;
		node4.next = node5;

		list(node1);

		ListNode node = deleteDuplicates2(node1);

		System.out.println();
		list(node);

	}

	/**
	 * 去掉链表中 重复的元素 因为链表是一个有序的了
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode deleteDuplicates(ListNode head) {
		// 当前的节点
		ListNode currentNode = head;
		while (currentNode != null && currentNode.next != null) {
			// 如果当前节点的 val值 与当前节点的下一个节点的val值相等的话，
			// 就把当前节点的next指向下一个节点的 下一个节点
			if (currentNode.val == currentNode.next.val) {
				// 这里应该在往下看一个节点 保证 3个 或者4个的情况发生
				int value = currentNode.val;
				ListNode tempNode = currentNode.next.next;
				while (tempNode != null && value == tempNode.val) {
					tempNode = tempNode.next;
				}
				currentNode.next = tempNode;
			}
			// 然后后移当前 的节点
			currentNode = currentNode.next;

		}

		return head;

	}

	/**
	 * 去掉链表中 重复的元素 因为链表是一个有序的了
	 * 
	 * @param head
	 * @return
	 */
	public static ListNode deleteDuplicates2(ListNode head) {
		// 当前的节点
		ListNode current = head;
		while (current != null && current.next != null) {
			System.out.println("id" + current.getId());
			if (current.next.val == current.val) {
				// 不会造成死循环, 因为current.next的指针, 在不停的后移
				current.next = current.next.next;
			} else {
				current = current.next;
			}

		}

		return head;

	}

	private static void list(ListNode head) {
		ListNode node = head;
		while (node != null) {
			System.out.print(node.val + "\t");
			node = node.next;
		}

	}

}
