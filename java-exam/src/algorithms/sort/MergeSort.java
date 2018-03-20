package algorithms.sort;

import java.util.Arrays;

/**
 * 归并排序 稳定的排序算法
 * O(nlogn)
 * 空间复杂度 O(n)
 *
 * @author MelloChan
 * @date 2018/3/20
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        array=mergeSort(array);
        System.out.println(Arrays.toString(array));
    }

    static int[] mergeSort(int[] array){
        if(array.length<2)return array;
        int mid=array.length/2;
        int[] left= Arrays.copyOfRange(array,0,mid);
        int[] right=Arrays.copyOfRange(array,mid,array.length);
        return merge(mergeSort(left),mergeSort(right));
    }

    static int[] merge(int[]left,int[] right){
        int[] result=new int[left.length+right.length];
        for (int index = 0,i=0,j=0; index <result.length ;++index) {
            if(i>=left.length){
                result[index]=right[j];
                j++;
            }else if(j>=right.length){
                result[index]=left[i];
                i++;
            }else if(left[i]>right[j]){
                result[index]=right[j];
                j++;
            }else{
                result[index]=left[i];
                i++;
            }
        }
        return result;
    }
}
