package algorithms.leetcode.tree;

import java.util.*;

/**
 * 树的 前中后序遍历
 * 递归与非递归
 *
 * @author MelloChan
 * @date 2018/3/30
 */
public class Traversal {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12);
        root.right.right.right = new TreeNode(13);
        root.right.right.left = new TreeNode(14);
        root.right.right.right = new TreeNode(15);
        preorder1(root);
        System.out.println();
        preorder2(root);
        System.out.println("\n----------------------------");
        inorder1(root);
        System.out.println();
        inorder2(root);
        System.out.println("\n----------------------------");
        postorder1(root);
        System.out.println();
        postorder2(root);
    }

    public static void preorder1(TreeNode root) {
        visit(root);
        if (root.left != null) {
            preorder1(root.left);
        }
        if (root.right != null) {
            preorder1(root.right);
        }
    }

    public static void preorder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.pop();
            visit(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    public static void inorder1(TreeNode root) {
        if (root.left != null) {
            inorder1(root.left);
        }
        visit(root);
        if (root.right != null) {
            inorder1(root.right);
        }
    }

    public static void inorder2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            visit(node);
            cur = node.right;
        }
    }

    public static void postorder1(TreeNode root) {
        if (root.left != null) {
            postorder1(root.left);
        }
        if (root.right != null) {
            postorder1(root.right);
        }
        visit(root);
    }

    public static void postorder2(TreeNode root) {
        List<Integer> ret=new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            ret.add(node.val);
            if(node.left!=null){
                stack.push(node.left);
            }
            if(node.right!=null){
                stack.push(node.right);
            }
        }
        Collections.reverse(ret);
        System.out.println(ret);
    }

    public static void visit(TreeNode root) {
        System.out.print(root.val + " ");
    }
}
