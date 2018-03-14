package algorithms.leetcode;

/**
 * 540. Single Element in a Sorted Array (Medium)
 * 一个有序数组只有一个数是唯一的 找出这个数
 * 例如 {2,2,3,3,4,5,5,6,6} 则-> 4
 * @author MelloChan
 * @date 2018/3/14
 */
public class SingleElement {
    public static void main(String[] args) {
        int[] nums={1,1,2,2,3,4,4,5,5,6,6,7,7,8,8};
        System.out.println(singleNonDuplicate(nums));
    }

    /**
     * 其他数都是出现两次而只有一个数出现一次
     * 因此可以使用异或  1 1 -> 0  0 0 -> 0  1 0 -> 1
     * 出现两次的数异或都变为0 最后唯一数异或0 得到唯一数
     * @param nums
     * @return
     */
    public static int singleNonDuplicate(int[] nums) {
        if(nums==null||nums.length<=0){
            return -1;
        }
        int length=nums.length;
        int target=nums[0];
        for(int i=1;i<length;++i){
            target^=nums[i];
        }
        return target;
    }
}
