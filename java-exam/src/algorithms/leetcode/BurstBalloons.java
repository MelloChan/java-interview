package algorithms.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Leetcode : 452. Minimum Number of Arrows to Burst Balloons (Medium)
 * 投飞镖刺破气球
 * 气球在一个水平数轴上摆放,横跨多个水平轴,即[start,end],飞镖从某点垂直射向坐标轴，只要该点在气球横跨范围,路径上的气球就会刺破。
 * 求解最小的投飞镖次数使所有气球都被刺破。
 * Input:
 * [[10,16], [2,8], [1,6], [7,12]]
 * Output:
 * 2
 * @author MelloChan
 * @date 2018/3/14
 */
public class BurstBalloons {
    public static void main(String[] args) {
        int[][] points={
                {10,16},{2,8},{1,6},{7,12}
        };
        System.out.println(findMinArrowShots(points));
    }

    /**
     *
     * 从第一个球的终止位置开始 若下一个球在该范围内则一同射爆
     * 否则以下一个位置作为起始点
     * @param points
     * @return
     */
    public static int findMinArrowShots(int[][] points) {
        if(points==null||points.length<=0){
            return -1;
        }
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));
        // 负终止点肯定小于第一个气球的起始点 射爆第一个气球 飞镖+1 终止点改为第一个气球的终止点
        long lastEnd=Long.MIN_VALUE;
        int count=0;
        for (int i = 0; i < points.length; ++i) {
            // 若小于第i个气球的起始点则需要重新投掷飞镖
            if(lastEnd<points[i][0]){
                // 重新赋值终止点 为第i个气球的终止点
                lastEnd=points[i][1];
                ++count;
            }// 否则不用投掷 在范围内的皆可射爆
        }
        return count;
    }
}
