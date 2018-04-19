package algorithms.partition;

/**
 * 数组中超过一半的某个数
 * @author MelloChan
 * @date 2018/4/19
 */
public class MoreThanHalfNum {
    public int moreThanHalfNum(int[] nums) {
        if (nums == null || nums.length <= 0) {
            return 0;
        }
        int length = nums.length, count = 1, num = nums[0];
        for (int n : nums) {
            count = num == n ? count + 1 : count - 1;
            if (count == 0) {
                num = n;
                count = 1;
            }
        }
        count = 0;
        for (int n : nums) {
            if (num == n) {
                ++count;
            }
        }
        return count > length / 2 ? num : 0;
    }
}
