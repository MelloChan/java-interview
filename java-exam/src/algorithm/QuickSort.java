package algorithm;

import java.util.Arrays;
/**
 * 快速排序-非稳定的排序算法
 * Created by MelloChan on 2017/12/19.
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        quickSort(0, array.length - 1, array);
        System.out.println(Arrays.toString(array));
    }

    static void quickSort(int left, int right, int[] array) {
        if (left < right) {
            int mid = partition(left, right, array);
            quickSort(left, mid - 1, array);
            quickSort(mid + 1, right, array);
        }
    }

    static int partition(int left, int right, int[] array) {
        int temp = array[left];
        while (left < right) {
            while (left < right && temp <= array[right]) --right;
            array[left] = array[right];
            while (left < right && temp >= array[left]) ++left;
            array[right] = array[left];
        }
        array[left] = temp;
        return left;
    }
}
