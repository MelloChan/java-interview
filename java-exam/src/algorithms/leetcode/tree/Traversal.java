package algorithms.leetcode.tree;

/**
 * 树的 前中后序遍历
 * 递归与非递归
 * @author MelloChan
 * @date 2018/3/30
 */
public class Traversal {
    public static void preorder1(TreeNode root) {
        visit(root);
        if(root.left!=null) {
            preorder1(root.left);
        }
        if(root.right!=null) {
            preorder1(root.right);
        }
    }

    public static void preorder2(TreeNode root) {

    }

    public static void inorder1(TreeNode root) {
        if(root.left!=null) {
            inorder1(root.left);
        }
        visit(root);
        if(root.right!=null) {
            inorder1(root.right);
        }
    }

    public static void inorder2(TreeNode root) {

    }

    public static void postorder1(TreeNode root) {
        if(root.left!=null) {
            postorder1(root.left);
        }
        if(root.right!=null) {
            postorder1(root.right);
        }
        visit(root);
    }

    public static void postorder2(TreeNode root) {

    }

    public static void visit(TreeNode root) {
        System.out.println(root.val+" ");
    }
}
