package algorithms.leetcode;

import java.util.Arrays;

/**
 * 167. Two Sum II - Input array is sorted
 * 从一个已经排序的数组中查找出两个数，使它们的和为target
 * @author MelloChan
 * @date 2018/3/18
 */
public class TwoSumII {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 11)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        if(numbers==null||numbers.length<=0){
            return null;
        }
        int first=0,second=numbers.length-1;
       while (first<second){
           int nums=numbers[first]+numbers[second];
           if(nums==target){
               return new int[]{first+1,second+1};
           }else if(nums<target){
               ++first;
           }else{
               --second;
           }
       }
       return null;
    }
}

