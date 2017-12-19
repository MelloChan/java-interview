package algorithm;

import java.util.Arrays;

import static algorithm.SwapUtil.swap;

/**
 * 冒泡排序
 * Created by MelloChan on 2017/12/19.
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        bubbleSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    static void bubbleSort(int[] array, int length) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swap(j, j + 1, array);
                }
            }
        }
    }

}
