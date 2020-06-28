package 二叉树;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。
 * 返回二叉搜索树（有可能被更新） 的根节点的引用。
 * 
 * 一般来说，删除节点可分为两个步骤：
 * 
 * 首先找到需要删除的节点； 如果找到了，删除它。 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * 
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 * 
 * @author mahaizhen
 *
 * @date 2020年6月2日
 */
public class _450_删除二叉搜索树中的节点 {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);
		root.right = new TreeNode(6);
//		root.left.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
//		root.right.left = new TreeNode("");
//		root.right.right = new TreeNode(7);

		_450_删除二叉搜索树中的节点 ceshi_ = new _450_删除二叉搜索树中的节点();
		TreeNode rootNode = ceshi_.deleteNode(root, 3);

		ceshi_.levelOrderTraversal(rootNode);

	}

	/**
	 *  
	 * @param root 根节点
	 * @param key 删除的节点的值
	 * @return  返回删除以后树的根节点
	 */
	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null || key == -1) {
			return root;
		}
		if (key > root.val) {
			root.right = deleteNode(root.right, key);
		} else if (key < root.val) {
			root.left = deleteNode(root.left, key);
		} else { // 这里是找到了 删除的节点, 执行下面删除的逻辑
			// 叶子节点
			if (root.left == null && root.right == null) {
				root = null;
				// 右子树 不为空 在去右子树中取查找删除
			} else if (root.right != null) {
				root.val = rightMin(root);
				root.right = deleteNode(root.right, root.val);
			} else if (root.left != null) {
				root.val = leftMax(root);
				root.left = deleteNode(root.left, root.val);
			}
		}

		return root;

	}

	/**
	 * 
	 * @param node 节点
	 * @return 找到当前节点的后继节点
	 */
	private int leftMax(TreeNode node) {
		if (node == null) {
			return -1;
		}
		TreeNode s = node.left;
		if (s != null) {
			while (s.right != null) {
				s = s.right;
			}
			return s.val;
		}
	
		return -1;
	
	}

	/**
	 * 
	 * @param node 节点
	 * @return 找到当前节点的后继节点
	 */
	private int rightMin(TreeNode node) {
		if (node == null) {
			return -1;
		}
		TreeNode s = node.right;
		if (s != null) {
			while (s.left != null) {
				s = s.left;
			}
			return s.val;
		}

		return -1;

	}


	/**
	 * 层序遍历
	 */
	private void levelOrderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		// 生成一个队列 并且放回root
		Queue<TreeNode> queue = new LinkedList<>();
		// 往队列里,添加元素
		queue.offer(root);
		while (!queue.isEmpty()) {
			// 从队头获取元素, 并且更新队列
			TreeNode current = queue.poll();
			System.out.println(current);
			TreeNode left = current.left;
			if (left != null) {
				queue.add(left);
			}
			TreeNode right = current.right;
			if (right != null) {
				queue.add(right);
			}
		}

	}

}
