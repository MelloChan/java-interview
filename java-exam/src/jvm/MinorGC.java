package jvm;

/**
 * 新生代GC
 * VM参数: -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * Created by MelloChan on 2018/1/5.
 */
public class MinorGC {
    private static final int _1MB=1024*1024;

    /**
     * 堆 20M  新生代 10M
     * SurivorRatio=8 8:1:1  Eden:From:To
     * @param args
     */
    public static void main(String[] args) {
        byte[] a1,a2,a3,a4;
        a1=new byte[_1MB*2];
        a2=new byte[_1MB*2];
        a3=new byte[_1MB*2];
        a4=new byte[_1MB*4]; // 新生代内存不够 出现MinorGC
    }
}/*
Heap
 PSYoungGen      total 9216K, used 8192K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
  eden space 8192K, 100% used [0x00000000ff600000,0x00000000ffe00000,0x00000000ffe00000)
  from space 1024K, 0% used [0x00000000fff00000,0x00000000fff00000,0x0000000100000000)
  to   space 1024K, 0% used [0x00000000ffe00000,0x00000000ffe00000,0x00000000fff00000)
 ParOldGen       total 10240K, used 4096K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  object space 10240K, 40% used [0x00000000fec00000,0x00000000ff000010,0x00000000ff600000)
 Metaspace       used 3279K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 359K, capacity 388K, committed 512K, reserved 1048576K
*/
