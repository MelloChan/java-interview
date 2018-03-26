package algorithms.pointers;

import algorithms.sort.SwapUtil;

import java.util.Arrays;

/**
 * 调整数组顺序使奇数位于偶数前面
 * @author MelloChan
 * @date 2018/3/26
 */
public class Reorder {
    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));
        reorder(arr);
        System.out.println(Arrays.toString(arr));
    }
    public static void reorder(int[] array) {
        if(array==null||array.length<=0){
            return;
        }
        int first=0,second=array.length-1;
        while (first<second){
            while ((first < second) && flag(array[first])) {
                ++first;
            }
            while ((first<second)&&!flag(array[second])){
                --second;
            }
            SwapUtil.swap(first,second,array);
        }
    }

    public static boolean flag(int value){
        if((value&1)==1){
            return true;
        }
        return false;
    }
}
