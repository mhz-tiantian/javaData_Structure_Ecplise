package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * 
 * 例如，
 * 
 * 给定二叉搜索树:
 * 
 * 4 / \ 2 7 / \ 1 3
 * 
 * 和值: 2 你应该返回如下子树:
 * 
 * 2 / \ 1 3 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 * 
 * 链接：https://leetcode-cn.com/problems/search-in-a-binary-search-tree
 * 
 * @author mahaizhen
 *
 * @date 2020年6月3日
 */
public class _700_二叉搜索树中的搜索 {

	public static void main(String[] args) {

	}

	/**
	 *  这中 是利用二分查找的 算法来的, 就是利用递归的方法来的
	 * @param root
	 * @param val
	 * @return
	 */
	public TreeNode searchBST(TreeNode root, int val) {
		if (root == null || val == root.val) {
			return root;
		}
		return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);

	}

	/**
	 *  第一个解法   层序遍历的方法 来
	 * @param root
	 * @param val
	 * @return
	 */
	private TreeNode node(TreeNode root, int val) {
		if (root == null) {
			return root;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode currentNode = queue.poll();
			if (currentNode.val == val) {
				return currentNode;
			}
			if (currentNode.left != null) {
				queue.offer(currentNode.left);
			}
			if (currentNode.right != null) {
				queue.offer(currentNode.right);
			}

		}
		return null;
	}

}
