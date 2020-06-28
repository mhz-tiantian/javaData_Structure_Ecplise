package 二叉树;

public class TreeNode {

	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}

	/**
	 * 有2个
	 * 
	 * @return
	 */
	boolean hasTwoChildNode() {
		return left != null && right != null;
	}

	@Override
	public String toString() {
		return val + "";
	}

}
