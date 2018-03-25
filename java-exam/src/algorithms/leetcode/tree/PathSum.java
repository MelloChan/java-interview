package algorithms.leetcode.tree;

/**
 * Leetcdoe : 112. Path Sum (Easy)
 * @author MelloChan
 * @date 2018/3/25
 */
public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        if(root.left==null&&root.right==null&&root.val==sum){
            return true;
        }
        return hasPathSum(root.left,sum-root.val)||hasPathSum(root.right,sum-root.val);
    }

}
