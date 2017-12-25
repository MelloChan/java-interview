package jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 使用unsafe分配本机内存 本机直接内存溢出
 * VM Args: -verbose:gc -Xmx 20M -XX:MaxDirectMemorySize=10M
 * Created by MelloChan on 2017/12/25.
 */
public class DirectMemoryOOM {
    private static final int _1MB=1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafe= Unsafe.class.getDeclaredFields()[0];
        unsafe.setAccessible(true);
        Unsafe unsafe1= (Unsafe) unsafe.get(null);
        while (true){
            unsafe1.allocateMemory(_1MB);
        }
    }
}
