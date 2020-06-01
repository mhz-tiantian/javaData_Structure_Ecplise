package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode-cn.com/problems/invert-binary-tree/
 * 
 * @author mahaizhen
 * 
 *         翻转一棵二叉树。
 *
 * @date 2020年6月1日
 */
public class _226_翻转二叉树 {

	/**
	 * 4 / \ 2 7 / \ / \ 1 3 6 9
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);

		invertTree2(root);

	}

	/**
	 * 利用前序遍历来交换
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		// 左右交换
		TreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		invertTree(root.left);
		invertTree(root.right);
		return root;

	}

	/**
	 * 后序遍历 来交换
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree2(TreeNode root) {
		if (root == null) {
			return root;
		}
		invertTree2(root.left);
		invertTree2(root.right);
		TreeNode tempNode = root.left;
		root.left = root.right;
		root.right = tempNode;
		return root;

	}

	/**
	 * 中序遍历来交换
	 * 
	 * @param root
	 * @return
	 */
	public static TreeNode invertTree3(TreeNode root) {
		if (root == null) {
			return root;
		}
		invertTree3(root.left);
		TreeNode tempNode = root.left;
		root.left = root.right;
		root.right = tempNode;
		// 因为 Right已经发生过交换了, 所以还是left
		invertTree3(root.left);

		return root;
	}

	/**
	 * 利用层序遍历 来交换 二叉树
	 * 
	 * @return
	 */
	public static TreeNode levelInvertTree(TreeNode root) {
		if (root == null) {
			return root;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 获得当前的 current
			TreeNode current = queue.poll();

			TreeNode left = current.left;
			TreeNode right = current.right;

			if (left != null) {
				queue.offer(left);
			}
			if (right != null) {
				queue.offer(right);
			}
			TreeNode temp = left;
			current.left = current.right;
			current.right = temp;

		}

		return root;

	}

}
