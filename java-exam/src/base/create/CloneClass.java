package base.create;

/**
 * 克隆方法 默认浅拷贝 即引用类型字段指向的仍是同一个
 * 注意浅拷贝与深拷贝
 * Created by MelloChan on 2018/1/1.
 */
public class CloneClass {
    public static void main(String[] args) throws CloneNotSupportedException {
        Clone c1 = new Clone(1,"clone");
        Clone c2 = (Clone) c1.clone();
        System.out.println(c1);
        System.out.println(c2);
    }
}

class Clone implements Cloneable {
    private int id;
    private String info;

    public Clone(int id, String info) {
        this.id = id;
        this.info = info;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
