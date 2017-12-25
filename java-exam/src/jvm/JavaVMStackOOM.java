package jvm;

/**
 * 通过递归->JVM栈溢出  -Xss设置栈容量
 * VM Args: -verbose:gc -Xss2M
 * 通过不断建立线程的方法倒是可以让栈产生OOM,这种情况下栈越大越快溢出,因为本质内存大小是不变的,因此每个线程的栈内存越大,则总的线程数量就越少
 * Created by MelloChan on 2017/12/25.
 */
public class JavaVMStackOOM {
    private void dontStop(){
        while (true){

        }
    }

    void stackByThread(){
        while (true){
           Thread thread=new Thread(() -> dontStop());
           thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM javaVMStackOOM=new JavaVMStackOOM();
        javaVMStackOOM.stackByThread();
    }
}
