package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区溢出
 * VM Args: -verbose:gc -XX:PermSize=10M -XX:MaxPermSize=10M
 * 实质上JDK1.7后就开始去永久代了
 * 因此这段代码并不会出现 PermGen space
 * 除非你是在JDK1.6及其后的版本
 * Created by MelloChan on 2017/12/25.
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        intern();
        List<String>list=new ArrayList<>();
        int i=0;
        while (true){
            list.add(String.valueOf(i++).intern());
        }
    }

    /**
     * 这段代码的趣点在于 JDK1.6版本会打印 false false 而之后的版本则是 true false
     * 这是因为JDK1.6 中会对首次出现的字符串复制到常量池永生代 而StringBuilder创建的字符串实例在堆上 因此地址肯定不一致
     * 而JDK1.7之后,会对首次出现的字符串在常量池中记录他的引用,而不是整个复制过来,因此打印true 而java在StringBuilder创建前已经存在常量池中了,
     * 因此返回的引用和StringBuilder创建的不一致
     */
    static void intern(){
        String str1=new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern()==str1);

        String str2=new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
    }
}
