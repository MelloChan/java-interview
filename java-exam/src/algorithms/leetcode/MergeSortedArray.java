package algorithms.leetcode;

import java.util.Arrays;

/**
 * 88. Merge Sorted Array
 * 合并两个有序数组
 *
 * @author MelloChan
 * @date 2018/3/19
 */
public class MergeSortedArray {

    /**
     * 将结果放置在nums1数组
     * @param nums1 容量足够 m + n
     * @param m     数组1的实际元素数量
     * @param nums2
     * @param n     数组2的实际元素数量
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {

        int[] sortArray = new int[m + n];
        int first = 0, second = 0;
        while (first < m && second < n) {
            if (nums1[first] <= nums2[second]) {
                sortArray[first + second] = nums1[first];
                ++first;
            } else {
                sortArray[first + second] = nums2[second];
                ++second;
            }
        }
        while (first < m) {
            sortArray[first + second] = nums1[first];
            ++first;
        }
        while (second < n) {
            sortArray[first + second] = nums2[second];
            ++second;
        }
        System.arraycopy(sortArray, 0, nums1, 0, first + second);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(sortArray));
    }
}
