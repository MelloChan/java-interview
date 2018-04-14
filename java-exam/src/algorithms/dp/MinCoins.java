package algorithms.dp;

/**
 * 换取的方法数
 * 给定一个数组arr 代表拥有的货币面值 每种拥有无限张
 * 一个整数aim 代表想要的钱数
 * 返回组成aim的最少货币数
 * @author MelloChan
 * @date 2018/3/29
 */
public class MinCoins {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3};
        // 4
        System.out.println(minCoins1(arr, 20));
        // 0
        System.out.println(minCoins1(arr, 0));
        // -1
        System.out.println(minCoins1(arr, 1));
    }

    public static int minCoins1(int[] arr, int aim) {
        if (arr == null || arr.length <= 0 || aim < 0) {
            return -1;
        }
        return 0;
    }

}
