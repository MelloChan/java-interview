package algorithms.leetcode;

/**
 * 680. Valid Palindrome II
 * 字符串可以(至多)删除一个字符，判断是否能构成回文字符串。
 * <p>
 * Example 1:
 * Input: "aba"
 * Output: True
 * <p>
 * Example 2:
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 *
 * @author MelloChan
 * @date 2018/3/19
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(validPalindrome("aba"));
        System.out.println(validPalindrome("abca"));
        System.out.println(validPalindrome("abbccca"));
        System.out.println(validPalindrome("eccer"));
        System.out.println(validPalindrome("ebcbbececabbacecbbcbe"));
        System.out.println(validPalindrome("aguokepatgbnvfqmgmlcupuufxoohdfpgjdmysgvhmvffcnqxjjxqncffvmhvgsymdjgpfdhooxfuupuculmgmqfvnbgtapekouga"));
    }

    /**
     * 双指针 当出现不相同的字符时 ①删除左边 ++first,遍历剩余字符  或者 ②删除右边 --second, 遍历剩余字符
     * 只要其中一种方法能构成回文即可
     * @param s
     * @return
     */
    public static boolean validPalindrome(String s) {
        if (s == null || s.length() <= 1) {
            return true;
        }

        int first = 0, second = s.length() - 1;
        while (first < second) {
            if (s.charAt(first) != s.charAt(second)) {
                return isPalindromic(s,first,second-1)||isPalindromic(s,first+1,second);
            }
            ++first;
            --second;
        }
        return true;
    }

    public static boolean isPalindromic(String s,int first,int second){
        while (first<second){
            if(s.charAt(first)!=s.charAt(second)){
                return false;
            }
            ++first;
            --second;
        }
        return true;
    }
}
