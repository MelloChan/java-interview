package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆溢出
 * -Xms 最小 -Xmx最大
 * VM Args: -verbose:gc -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * Created by MelloChan on 2017/12/25.
 */
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}/*
[GC (Allocation Failure)  5632K->3185K(19968K), 0.0082041 secs]
[GC (Allocation Failure)  8817K->7896K(19968K), 0.0119397 secs]
[Full GC (Ergonomics)  16693K->12484K(19968K), 0.3521616 secs]
[Full GC (Ergonomics)  16606K->16461K(19968K), 0.2584007 secs]
[Full GC (Allocation Failure)  16461K->16443K(19968K), 0.2947890 secs]
java.lang.OutOfMemoryError: Java heap space
Dumping heap to java_pid3288.hprof ...
Heap dump file created [28178185 bytes in 0.129 secs]
*/
