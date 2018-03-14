package algorithms.leetcode;


/**
 * 441.摆硬币
 * 可以利用二分查找
 * @author MelloChan
 * @date 2018/3/14
 */
public class ArrangeCoins {
    public static void main(String[] args) {
        System.out.println(arrangeCoins1(6));
        System.out.println(arrangeCoins1(8));
        System.out.println(arrangeCoins1(45));
        System.out.println(arrangeCoins2(45));
    }

    /**
     * 直观点的解法
     * @param n
     * @return
     */
    public static int arrangeCoins1(int n) {
        if(n<=0) {
            return n;
        }
        int level=1;
        while (n>0){
            n-=level;
            ++level;
        }
        return n==0?level-1:level-2;
    }

    /**
     * 二分查找思想 利用等差数列求总和
     * 假设输入为8 求出中值4 摆四行需要1+2+3+4=10>8
     * @param n
     * @return
     */
    public static int arrangeCoins2(int n) {
        if(n<=0) {
            return n;
        }
        int low=0,high=n;
        while (low<=high){
            int mid=low+(high-low)/2;
            long sum=(mid*(mid+1L))/2;
            if(sum==n){
                return mid;
            }else if(sum<n){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return high;
    }
}
