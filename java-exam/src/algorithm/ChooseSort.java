package algorithm;

import java.util.Arrays;

import static algorithm.SwapUtil.swap;

/**
 * 选择排序-交换排序的一种
 * Created by MelloChan on 2017/12/19.
 */
public class ChooseSort {
    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        chooseSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 选择排序
     * @param array
     * @param length
     */
    static void chooseSort(int[] array, int length) {
        if(array==null||length<=0){
            return;
        }
        for (int i = 0; i < length; i++) {
            //假设最小值索引
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                //找到比当前最小值还小的值
                if (array[minIndex] > array[j]) {
                    // 更新最小值索引
                    minIndex = j;
                }
            }
            //上面的遍历如果有更新最小值索引
            if (minIndex != i) {
                //则替换
                swap(minIndex, i, array);
            }
        }
    }
}
