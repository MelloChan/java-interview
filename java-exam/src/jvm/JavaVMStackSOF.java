package jvm;

/**
 * 通过递归->JVM栈溢出  -Xss设置栈容量,实质上总是先产生SOF,因为栈容量总是先行溢出了,而内存容量还绰绰有余
 * 不管是减少栈内存容量还是定义了大量的本地变量,增大此方法帧中本地变量表的长度,结果都是抛出SOF
 * 如果通过不断建立线程的方法倒是可以让栈产生OOM,这种情况下栈越大越快溢出,因为本质内存大小是不变的,因此每个线程的栈内存越大,则总的线程数量就越少
 * VM Args: -verbose:gc -Xss128k
 * Created by MelloChan on 2017/12/25.
 */
public class JavaVMStackSOF {
    private int length = 1;

    void stackLeak() {
        length++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF javaVMStackSOF = new JavaVMStackSOF();
        try {
            javaVMStackSOF.stackLeak();
        } catch (Throwable e) {
            System.out.println("Stack Length:" + javaVMStackSOF.length);
            throw e;
        }
    }
}/*
Stack Length:984
*/
