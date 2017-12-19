package algorithm;

import java.util.Arrays;

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

