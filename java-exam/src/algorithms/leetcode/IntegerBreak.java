package algorithms.leetcode;

/**
 * 343. Integer Break
 * 分解为正整数 使其乘积最大
 * 例如: 2 = 1 + 1 -> max =  1*1
 *       5=  2 + 3 -> max = 2*3
 *       10= 3 + 3 + 4 -> max = 3*3*4
 * @author MelloChan
 * @date 2018/3/30
 */
public class IntegerBreak {
    public static void main(String[] args) {
        System.out.println(integerBreak(12));
        System.out.println(integerBreak(10));
        System.out.println(integerBreak(4));
        System.out.println(integerBreak(3));
        System.out.println(integerBreak(2));
    }
    /**
     * 均值不等式
     * 假设 a = n*x  b = x^n = x^(a/x) = (x^(1/x))^a -> x^(1/x) 趋近于2.8 分解为正整数可视为 3
     * 因此尽量分解多个3
     * @param n
     * @return
     */
    public static int integerBreak(int n) {
        while (n<=3){
            return n-1;
        }

        int max=1;
        while (n>4){
            max*=3;
            n-=3;
        }
        max*=n;
        return max;
    }
}
