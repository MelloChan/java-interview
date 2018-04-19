package algorithms.pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 递增序列中值为target的两个整数
 * @author MelloChan
 * @date 2018/4/19
 */
public class NumbersWithSum {
    public List<Integer> findNumbersWithSum(int[] nums, int target) {
        if (nums == null || nums.length <= 0) {
            return null;
        }
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new ArrayList<>(Arrays.asList(nums[left], nums[right]));
            } else if (sum < target) {
                ++left;
            } else {
                --right;
            }
        }
        return null;
    }
}
