package algorithms.sort;

import java.util.Arrays;

/**
 * 冒泡排序-交换排序的一种 稳定
 * 平均复杂度O(n2)
 * best O(n)  worst O(n2)
 *
 * @author MelloChan
 * @date 2017/12/19
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        bubbleSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 冒泡排序
     * @param array
     * @param length
     */
    static void bubbleSort(int[] array, int length) {
        if(array==null||length<=0){
            return;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    SwapUtil.swap(j, j + 1, array);
                }
            }
        }
    }

}

