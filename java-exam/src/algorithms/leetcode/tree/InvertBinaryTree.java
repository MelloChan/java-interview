package algorithms.leetcode.tree;

/**
 * Leetcode : 226. Invert Binary Tree (Easy)
 * @author MelloChan
 * @date 2018/3/25
 */
public class InvertBinaryTree {
    public static TreeNode invertTree(TreeNode root) {
        if(root==null){
            return null;
        }
        TreeNode left=root.left;
        root.left=invertTree(root.right);
        root.right=invertTree(left);
        return root;
    }
}
