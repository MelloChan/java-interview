package base.serializable;

import java.io.*;

/**
 * 手动序列化与反序列化字段
 * Created by MelloChan on 2018/2/13.
 */
public class ExternalizableDemo implements Externalizable {
    private String s;
    private int i;

    public ExternalizableDemo() {
        System.out.println(ExternalizableDemo.class.getName());
    }

    public ExternalizableDemo(String s, int i) {
        System.out.println(ExternalizableDemo.class.getName() + "(String s,int i)");
        this.s = s;
        this.i = i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        System.out.println("writeExternal");
        // 必须明确写入想要序列化的字段
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("readExternal");
        // 必须明确读取反序列化的字段 否则s与i都将是初始化的零值(null 0)
        s = (String) in.readObject();
        i = in.readInt();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("Construting objects:");
        ExternalizableDemo demo = new ExternalizableDemo("mello", 21);
        System.out.println(demo);

        ObjectOutput out = new ObjectOutputStream(
                new FileOutputStream("demo.out")
        );
        System.out.println("Saveing objects:");
        out.writeObject(demo);
        out.close();

        ObjectInput in = new ObjectInputStream(
                new FileInputStream("demo.out")
        );
        System.out.println("Recovering objects:");
        demo = (ExternalizableDemo) in.readObject();
        System.out.println(demo);
    }

    @Override
    public String toString() {
        return "ExternalizableDemo{" +
                "s='" + s + '\'' +
                ", i=" + i +
                '}';
    }
}/* output:
Construting objects:
base.serializable.ExternalizableDemo(String s,int i)
ExternalizableDemo{s='mello', i=21}
Saveing objects:
writeExternal
Recovering objects:
base.serializable.ExternalizableDemo
readExternal
ExternalizableDemo{s='mello', i=21}
*/
