package algorithm;

import java.util.Arrays;

import static algorithm.SwapUtil.swap;

/**
 * 选择排序
 * Created by MelloChan on 2017/12/19.
 */
public class ChooseSort {
    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        chooseSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    static void chooseSort(int[] array, int length) {
        for (int i = 0; i < length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < length; j++) {
                if (array[minIndex] > array[j]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                swap(minIndex, i, array);
            }
        }
    }
}
