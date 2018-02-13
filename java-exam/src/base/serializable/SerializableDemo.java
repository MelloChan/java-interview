package base.serializable;

import java.io.*;

/**
 * 序列化简单演示
 * Created by MelloChan on 2018/2/13.
 */
public class SerializableDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 序列化至文本文件
        ObjectOutput out=new ObjectOutputStream(
                new FileOutputStream("demo.txt")
        );
        out.writeObject(new Demo());

        // 反序列化
        ObjectInput in=new ObjectInputStream(
                new FileInputStream("demo.txt")
        );
        System.out.println(in.readObject().getClass().getName());
    }
}/* output:
base.serializable.Demo
*/
