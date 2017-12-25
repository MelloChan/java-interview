package jvm;

/**
 * 借助CGLIB 使方法区出现内存溢出异常 过多的动态增强类 导致需要更大的方法区来保证新的类的载入
 * 此处需要引入CGLIB工具包 除了CGLIB外,常见的大量JSP或动态生成JSP文件的应用/基于OSGI的应用也会导致方法区溢出
 * VM Args: -verbose:gc -XX:PermSize=10M -XX:MaxPermSize=10M
 * Created by MelloChan on 2017/12/25.
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        // 参考深入理解JVM虚拟机 P58
    }
}
