package algorithms.leetcode;

/**
 * 69.求开方(非精确)
 * 利用二分查找
 * @author MelloChan
 * @date 2018/3/14
 */
public class MySqrt {
    public static void main(String[] args) {
        System.out.println(mySqrt(4));
        // 2.82842
        System.out.println(mySqrt(8));
    }
    public static int mySqrt(int x) {
        if(x<=1) {
            return x;
        }
        int low=1,high=x;
        while (low<=high){
            int mid=low+(high-low)/2;
            int sqrt=x/mid;
            if(sqrt==mid) {
                return sqrt;
            } else if(sqrt<mid) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
}
