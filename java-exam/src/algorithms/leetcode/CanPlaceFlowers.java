package algorithms.leetcode;

/**
 * 605. Can Place Flowers
 * 有连续的花盆可以放花,但花之间不能相邻
 * Example 1:
 * Input: flowerbed = [1,0,0,0,1], n = 1
 * Output: True
 * <p>
 * Example 2:
 * Input: flowerbed = [1,0,0,0,1], n = 2
 * Output: False
 *
 * @author MelloChan
 * @date 2018/3/16
 */
public class CanPlaceFlowers {
    public static void main(String[] args) {

    }

    /**
     * 只要当前花盆没有花且前面后面也没有花 那我们就可以放花
     *
     * @param flowerbed 一组连续花盆 只有 0/1 0表示没有花 1 表示有
     * @param n         要放入的花数量
     * @return 是否能成功放入
     */
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;
        for (int i = 0; i < length; i++) {
            if (n == 0) {
                return true;
            }
            if (flowerbed[i] == 0) {
                // 注意处理边界情况
                int pre = (i == 0 ? 0 : flowerbed[i - 1]);
                int next = (i == length - 1 ? 0 : flowerbed[i + 1]);
                if (pre + next == 0) {
                    --n;
                }
            }
        }
        return n == 0;
    }
}
