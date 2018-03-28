package algorithms.dp;

/**
 * 最长公共子串
 * @author MelloChan
 * @date 2018/3/28
 */
public class LCS {
    public static String lcs(String s1,String s2){
        if(s1==null||s2==null||"".equals(s1)||"".equals(s2)){
            System.out.println(0);
        }
        char[] c1=s1.toCharArray();
        char[] c2=s2.toCharArray();
        int row=0,col=c2.length-1;
        int max=0,end=0;
        while (row<c1.length){
            int i=row;
            int j=col;
            int len=0;
            while (i<c1.length&&j<c2.length){
                if(c1[i]!=c2[j]){
                    len=0;
                }else{
                    len++;
                }
                if(len>max){
                    end=i;
                    max=len;
                }
                i++;
                j++;
            }
            if(col>0){
                col--;
            }else{
                row++;
            }
        }
        return s1.substring(end-max+1,end+1);
    }
}
