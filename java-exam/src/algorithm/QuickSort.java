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
        int temp=array[left];
        int l=left,r=right;
        while (l!=r){
            while (l<r&&temp<=array[r])--r;
            while (l<r&&temp>=array[l])++l;
            if(l<r){
                SwapUtil.swap(l,r,array);
            }
        }
        array[left]=array[l];
        array[l]=temp;
        return l;
    }
}
