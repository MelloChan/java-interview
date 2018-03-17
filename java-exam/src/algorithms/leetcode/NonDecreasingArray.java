package algorithms.leetcode;

/**
 * 665. Non-decreasing Array
 * 判断一个数组能不能只修改一个数就成为非递减数组。
 * Input: [4,2,3]
 * Output: True
 * Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
 * <p>
 * Input: [4,2,1]
 * Output: False
 * Explanation: You can't get a non-decreasing array by modify at most one element.
 *
 * @author MelloChan
 * @date 2018/3/17
 */
public class NonDecreasingArray {
    public static void main(String[] args) {
        int nums[]={1,2,3,4,2};
        System.out.println(checkPossibility(nums));
    }

    /**
     * 显然当第i个数大于第i+1个数时就要进行修改
     * 此时有两种情况 ① nums[i+1] > nums[i-1] 则nums[i] = nums[i+1]
     *                ② nums[i+1] < nums[i-1] 则nums[i+1] = nums[i]
     * 当然还要处理边界情况 另外递减情况超过一次就可以退出循环了
     * @param nums
     * @return
     */
    public static boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return true;
        }
        int length=nums.length-1,count=0;
        for (int i = 0; i < length; i++) {
            if(nums[i]>nums[i+1]){
                if(i==0||nums[i+1]>nums[i-1]){
                    nums[i]=nums[i+1];
                }else if(nums[i+1]<nums[i-1]){
                    nums[i+1]=nums[i];
                }
                ++count;
            }
            if(count>1){
                break;
            }
        }
        return count<=1;
    }
}
