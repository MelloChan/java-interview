package base;

/**
 * 类的初始化顺序
 * Created by MelloChan on 2017/12/19.
 */
public class InitDemo {
    public static void main(String[] args) {
        Son son=new Son();
        System.out.println("Son->"+son);
    }
    @Override
    public String toString() {
        return "InitDemo instance!";
    }
}/* output:
Base->I am static block:InitDemo instance!
Son->I am static block:InitDemo instance!
Base->I am ordinary block:InitDemo instance!
Base->I am Construction!
initDemo1:InitDemo instance!
initDemo2:InitDemo instance!
Base Over!
Son->I am ordinary block:InitDemo instance!
Son->I am Construction!
initDemo1:InitDemo instance!
initDemo2:InitDemo instance!
Son Over!
Son->base.Son@4554617c
*/
class Base{
    private static InitDemo initDemo1=new InitDemo();
    static {
        System.out.println("Base->I am static block:"+initDemo1);
    }
    private InitDemo initDemo2=new InitDemo();
    {
        System.out.println("Base->I am ordinary block:"+initDemo2);
    }
    public Base(){
        System.out.println("Base->I am Construction!");
        System.out.println("initDemo1:"+initDemo1);
        System.out.println("initDemo2:"+initDemo2);
        System.out.println("Base Over!");
    }
}
class Son extends Base{
    private static InitDemo initDemo1=new InitDemo();
    static {
        System.out.println("Son->I am static block:"+initDemo1);
    }
    private InitDemo initDemo2=new InitDemo();
    {
        System.out.println("Son->I am ordinary block:"+initDemo2);
    }
    public Son(){
        System.out.println("Son->I am Construction!");
        System.out.println("initDemo1:"+initDemo1);
        System.out.println("initDemo2:"+initDemo2);
        System.out.println("Son Over!");
    }
}