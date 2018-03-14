package algorithms.leetcode;

import java.util.Arrays;

/**
 * Leetcode : 455. Assign Cookies (Easy)
 * 题目描述：每个孩子都有一个满足度，每个饼干都有一个大小，
 * 只有饼干的大小大于一个孩子的满足度，该孩子才会获得满足。
 * 求解最多可以获得满足的孩子数量。
 * 因为最小的孩子最容易得到满足，因此先满足最小孩子。
 * 给一个孩子的饼干应当尽量小又能满足该孩子，这样大饼干就能拿来给满足度比较大的孩子。
 * @author MelloChan
 * @date 2018/3/14
 */
public class AssignCookies {
    public static void main(String[] args) {
        int[] g={1,2,3,4,5};
        int[] s={1,2,3};
        System.out.println(findContentChildren(g,s));

        g= new int[]{6, 5, 4, 3, 2, 1};
        s=new int[]{1,2,3,4};
        System.out.println(findContentChildren(g,s));
    }

    /**
     * 贪心算法 只有最少饼干需求的孩子满足了才考虑下一个孩子的饼干需求
     * @param g 几个孩子 每个孩子的满足度
     * @param s 几块饼干 每块饼干的大小
     * @return 输出几个孩子能得到满足
     */
    public static int findContentChildren(int[] g, int[] s) {
        if(g==null||s==null){
            return -1;
        }
        Arrays.sort(g);
        Arrays.sort(s);

        int gLength=g.length;
        int sLength=s.length;
        int i=0,j=0;
        while (i<gLength&&j<sLength){
            // 如果第i个孩子给予第j块饼干能得到满足则开始考虑下个孩子
            if(g[i]<=s[j]){
                ++i;
            }// 孩子不满足则考虑下一块饼干 优先低需求的孩子
            // 满不满足都考虑下一块饼干
            ++j;
        }
        return i;
    }
}
