package algorithms.recursion;

/**
 * 跳台阶
 * @author MelloChan
 * @date 2018/4/19
 */
public class JumpFloor {
    public int jumpFloor(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int first = 1, second = 0, result = 0;
        for (int i = 1; i <= n; i++) {
            result = first + second;
            first = second;
            second = result;
        }
        return result;
    }
}
