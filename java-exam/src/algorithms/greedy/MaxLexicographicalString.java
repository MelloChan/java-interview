package algorithms.greedy;

/**
 * 字符串的最长字典序子串
 *
 * @author MelloChan
 * @date 2018/4/20
 */
public class MaxLexicographicalString {
    String lexicographicalString(String s) {
        StringBuilder sb = new StringBuilder();
        int length = s.length();
        int max = 0;
        for (int i = length - 1; i >= 0; --i) {
            if (s.charAt(i) >= max) {
                max = s.charAt(i);
                sb.append(s.charAt(i));
            }
        }
        return sb.reverse().toString();
    }
}
