package algorithms.sort;

import java.util.Arrays;

/**
 * 希尔排序-插入排序的一种,比直接插入排序更高效,是非稳定的
 * O(nlogn)
 * @author MelloChan
 * @date 2017/12/23
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        shellSort(array.length , array);
        System.out.println(Arrays.toString(array));
    }
    static void shellSort(int length,int[] array){
        int temp,gap=length/2;
        while (gap>0){
            for (int i = gap; i < length; i++) {
                temp=array[i];
                int preIndex=i-gap;
                while (preIndex>=0&&array[preIndex]>temp){
                    array[preIndex+gap]=array[preIndex];
                    preIndex-=gap;
                }
                array[preIndex+gap]=temp;
            }
            gap/=2;
        }
    }
}
