package 链表;

public class ListNode {

	int val;
	int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	ListNode next;

	public ListNode(int val) {
		this.val = val;
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + "]";
	}

}
