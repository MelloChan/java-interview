package algorithms.leetcode;

/**
 * 二分查找
 * Java中有自带的二分查找方法 在工具包的Arrays类
 * @author MelloChan
 * @date 2018/3/14
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] array={1,2,3,4,5,6,7,10,11,23,30,33};
        int index=binarySearch(11,array);
        System.out.println(array[index]+":"+index);
    }

    public static int binarySearch(int target, int[] array) {
        if (array == null || array.length <= 0) {
            return -1;
        }
        int low = 0, high = array.length - 1;
       while (low<=high){
            // 取中 防止溢出
            int mid = low + (high - low) /2;
            if (target == array[mid]) {
                return mid;
            } else if (target < array[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}
