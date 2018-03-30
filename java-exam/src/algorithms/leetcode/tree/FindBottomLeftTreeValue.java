package algorithms.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513. Find Bottom Left Tree Value
 * @author MelloChan
 * @date 2018/3/30
 */
public class FindBottomLeftTreeValue {
    public static int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            root=queue.poll();
            // 区别关键点
            if(root.right!=null){
                queue.add(root.right);
            }
            if(root.left!=null){
                queue.add(root.left);
            }
        }
        return root.val;
    }
}
