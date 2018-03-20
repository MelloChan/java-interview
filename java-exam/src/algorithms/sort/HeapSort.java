package algorithms.sort;

import java.util.Arrays;

/**
 * 堆排序 不稳定排序
 * O(nlogn)
 * 基于二叉树构建最大堆 OR 最小堆
 * @author MelloChan
 * @date 2018/3/20
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        array=heapSort(array);
        System.out.println(Arrays.toString(array));
    }

    static int len;

    static int[] heapSort(int[] array) {
        len = array.length;
        if (len < 1) return array;
        buildMaxHeap(array);

        while (len > 0) {
            SwapUtil.swap(0, len - 1, array);
            len--;
            adjustHeap(array, 0);
        }
        return array;
    }

    static void buildMaxHeap(int[] array) {
        for (int i = (len - 1) / 2; i >= 0; i--) {
            adjustHeap(array, i);
        }
    }

    static void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        if (i * 2 < len && array[i * 2] > array[maxIndex]) {
            maxIndex = i * 2;
        }
        if (i * 2 + 1 < len && array[i * 2 + 1] > array[maxIndex]) {
            maxIndex = i * 2 + 1;
        }
        if (maxIndex != i) {
            SwapUtil.swap(maxIndex, i, array);
            adjustHeap(array, maxIndex);
        }
    }
}
