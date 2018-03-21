package algorithms.leetcode;

/**
 * Leetcode : 300. Longest Increasing Subsequence (Medium)
 * 动态规划
 * @author MelloChan
 * @date 2018/3/21
 */
public class LongestIncreasingSubsequence {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }

        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int max = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        int ref = 1;
        for (int i = 0; i < n; i++) {
            ref = Math.max(ref, dp[i]);
        }
        return ref;
    }
}
