package algorithms.pointers;

/**
 * 我们把只包含因子 2、3 和 5 的数称作丑数（Ugly Number）。求从小到大的顺序的第 1500个丑数。
 * 例如 6、8 都是丑数，但 14 不是，它包含因子 7。习惯上我们把 1 当做第一个丑数。
 *
 * @author MelloChan
 * @date 2018/3/26
 */
public class UglyNumber {
    public static void main(String[] args) {
        System.out.println(isUglyNumber(3));
        System.out.println(isUglyNumber(1500));
    }

    /**
     * 根据传递的参数n来构建一个丑数数组 大小依次递增 返回相应数组位置上的丑数值
     * 空间换时间的概念 防止计算多余的非丑数
     * @param n
     * @return
     */
    public static int isUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] nums = new int[n];
        nums[0] = 1;
        int nextUglyIndex = 1;
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;
        while (nextUglyIndex < n) {
            int min = min(nums[p1] * 2, nums[p2] * 3, nums[p3] * 5);
            nums[nextUglyIndex] = min;
            while (nums[p1] * 2 <= nums[nextUglyIndex]) {
                ++p1;
            }
            while (nums[p2] * 3 <= nums[nextUglyIndex]) {
                ++p2;
            }
            while (nums[p3] * 5 <= nums[nextUglyIndex]) {
                ++p3;
            }
            ++nextUglyIndex;
        }
        return nums[--nextUglyIndex];
    }

    public static int min(int x, int y, int z) {
        int min = x < y ? x : y;
        return min < z ? min : z;
    }
}
