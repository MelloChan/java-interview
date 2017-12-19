package algorithm;

/**
 * 替换工具
 * Created by MelloChan on 2017/12/19.
 */
public class SwapUtil {
    static void swap(int first, int second, int[] array) {
        // 异或替换
        array[first] ^= array[second];
        array[second] ^= array[first];
        array[first] ^= array[second];
        /* 差值替换
        int dValue = array[first] - array[second];
        array[second] = dValue + array[second];
        array[first] = array[first] - dValue;

          中间值替换
        int temp = array[first];
        array[first] = array[second];
        array[second] = temp;
         */
    }
}
