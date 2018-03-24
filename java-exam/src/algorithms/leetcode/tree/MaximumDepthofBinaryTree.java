package algorithms.leetcode.tree;

/**
 * 104. Maximum Depth of Binary Tree
 * @author MelloChan
 * @date 2018/3/24
 */
public class MaximumDepthofBinaryTree {
    public static int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}
