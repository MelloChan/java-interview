package algorithms.leetcode;

/**
 * Leetcode : 633. Sum of Square Numbers (Easy)
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 * @author MelloChan
 * @date 2018/3/18
 */
public class SumofSquareNumbers {
    public static void main(String[] args) {
        System.out.println(judgeSquareSum(2));
    }

    /**
     * 双指针 关键是要知道从根号c开始
     * @param c
     * @return
     */
    public static boolean judgeSquareSum(int c) {
        int first=0,second= (int) Math.sqrt(c);
        while (first<=second){
            int sum=first*first+second*second;
            if(sum==c){
                return true;
            }else if(sum<c){
                ++first;
            }else{
                --second;
            }
        }
        return false;
    }
}
