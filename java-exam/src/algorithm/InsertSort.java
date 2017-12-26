package algorithm;

import java.util.Arrays;

/**
 * 直接插入排序-稳定的排序算法
 * Created by MelloChan on 2017/12/19.
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] array = {12, 2, 14, 6, 83, 656, 215, 0, 2, 3};
        insertSort(array, array.length);
        System.out.println(Arrays.toString(array));
    }

    /**
     * 插入排序
     * @param array
     * @param length
     */
    static void insertSort(int[] array, int length) {
        if(array==null||length<=0){
            return;
        }
        //从当前数组第二个数开始
        for (int i = 1; i < length; i++) {
            int j = i;
            // 保存当前选中的比较值
            int temp = array[i];
            // 如果j>0 且 当前保存值小于后续值
            while (j > 0 && temp<array[j-1]) {
                //则替换j索引的值 即覆盖掉原先的值
                array[j] = array[j - 1];
                --j;
            }
            // 最后更新j索引处的值
            array[j] = temp;
        }
    }
}
