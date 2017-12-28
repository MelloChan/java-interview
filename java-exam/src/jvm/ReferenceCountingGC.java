package jvm;

/**
 * 这种对象间的引用 在使用引用计数法的GC存活判断时将导致无法GC
 * 实际上虚拟机并不会采用这种算法
 * Created by MelloChan on 2017/12/28.
 */
public class ReferenceCountingGC {
    private Object instance=null;
    private static final int _1MB=1024*1024;

    private byte[] bytes=new byte[2*_1MB];

    public static void main(String[] args) {
        ReferenceCountingGC a=new ReferenceCountingGC();
        ReferenceCountingGC b=new ReferenceCountingGC();
        a.instance=b;
        b.instance=a;

        a=null;
        b=null;
        System.gc();
    }
}
