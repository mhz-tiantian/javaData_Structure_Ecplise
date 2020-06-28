package 二叉树;

/**
 * 
 * @author mahaizhen
 *
 * @date 2020年6月3日
 * 
 *       给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 保证原始二叉搜索树中不存在新值。
 * 
 *       注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。
 * 
 *       例如, 
 * 
 *       给定二叉搜索树:
 * 
 *       4 / \ 2 7 / \ 1 3
 * 
 *       和 插入的值: 5 你可以返回这个二叉搜索树:
 * 
 *       4 / \ 2 7 / \ / 1 3 5 或者这个树也是有效的:
 * 
 *       5 / \ 2 7 / \ 1 3 \ 4
 * 
 *       链接：https://leetcode-cn.com/problems/insert-into-a-binary-search-tree
 */
public class _701_二叉搜索树中的插入操作 {

	public static void main(String[] args) {

	}

	public TreeNode insertIntoBST(TreeNode root, int val) {
		if (root == null) {
			root = new TreeNode(val);
			return root;
		}
		TreeNode node = root;
		TreeNode parentNode = root; // 记录出来父节点
		int cmp = 0; // 记录添加的方向
		while (node != null) {
			cmp = val - node.val;
			parentNode = node;
			if (cmp > 0) {
				// 添加的数据比 当前的节点大, 去右子树去查找
				node = node.right;
			} else if (cmp < 0) {
				// 添加的数据比当前的数据小, 去左子树去朝招
				node = node.left;
			} else {
				return root;
			}
		}
		if (cmp > 0) {
			parentNode.right = new TreeNode(val);
		} else {
			parentNode.left = new TreeNode(val);
		}

		return root;

	}

}
