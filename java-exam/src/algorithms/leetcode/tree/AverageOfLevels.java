package algorithms.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 637. Average of Levels in Binary Tree
 * 计算每一层节点平均值
 * @author MelloChan
 * @date 2018/3/30
 */
public class AverageOfLevels {

    /**
     * 计算每一层节点的平均值
     * @param root
     * @return
     */
    public static List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        List<Double> list=new ArrayList<>();
        queue.add(root);
        int size;
        while((size=queue.size())>0){
            double sum=0;
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                sum+=node.val;
                if(node.left!=null) {
                    queue.add(node.left);
                }
                if(node.right!=null) {
                    queue.add(node.right);
                }
            }
            list.add(sum/size);
        }
        return list;
    }

    /**
     * 层序遍历
     * @param root
     * @return
     */
    public static List<Integer> traversal(TreeNode root) {
        Queue<TreeNode> queue=new LinkedList<>();
        List<Integer> list=new ArrayList<>();
        queue.add(root);
        while (queue.size()>0){
            TreeNode node=queue.poll();
            list.add(node.val);
            if(node.left!=null){
                queue.add(node.left);
            }
            if(node.right!=null){
                queue.add(node.right);
            }
        }
        return list;
    }
}
