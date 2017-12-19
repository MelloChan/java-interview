package base;

/**
 *  == & equals
 * Created by MelloChan on 2017/12/19.
 */
public class Compare {
    public static void main(String[] args) {
        Compare.compareByString();
        System.out.println("--------------------------------");
        // 注意Integer的数字缓存 -128~127
        Compare.compareByInteger();
    }
    static void compareByString(){
        String s1="Hello!";
        String s2="Hello!";
        String s3=new String("Hello!");
        String s4=new String("Hello!");
        System.out.println(s1==s2); //true
        System.out.println(s1==s3); // false
        System.out.println(s1.equals(s3)); // true
        System.out.println(s3==s4); // false
        System.out.println(s3.equals(s4));  // true
    }
    static void compareByInteger(){
        Integer i1=1;
        Integer i2=1;
        Integer i3=128;
        Integer i4=128;
        Integer i5=new Integer(1);
        Integer i6=new Integer(128);
        System.out.println(i1==i2); // true
        System.out.println(i3==i4); // false
        System.out.println(i1==i5); // false
        System.out.println(i1.equals(i5)); // true
        System.out.println(i3==i6); // false
        System.out.println(i3.equals(i6)); // true
    }
}

