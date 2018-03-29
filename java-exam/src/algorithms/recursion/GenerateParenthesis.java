package algorithms.recursion;

import java.util.*;

/**
 * 22. Generate Parentheses
 * 输入整数n 输出匹配的'(' ')'
 * @author MelloChan
 * @date 2018/3/29
 */
public class GenerateParenthesis {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(generateParentheses(n));
    }
    public static List<String> generateParentheses(int n) {
        Set<String> res = new HashSet<>();
        if (n == 0) {
            res.add("");
        } else {
            List<String> pre = generateParentheses(n - 1);
            for (String str : pre) {
                for (int i = 0; i < str.length(); ++i) {
                    if (str.charAt(i) == '(') {
                        str = str.substring(0, i + 1) + "()" + str.substring(i + 1, str.length());
                        res.add(str);
                        str = str.substring(0, i + 1) +  str.substring(i + 3, str.length());
                    }
                }
                res.add("()" + str);
            }
        }

        return new ArrayList(res);
    }
}
