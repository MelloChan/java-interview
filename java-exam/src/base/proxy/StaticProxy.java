package base.proxy;

/**
 * 静态代理
 * Created by MelloChan on 2018/1/17.
 */
public class StaticProxy {
    public static void consumer(Interface i) {
        i.doSomething();
        i.somethingElse("proxy");
    }

    public static void main(String[] args) {

        consumer(new RealObject());
        System.out.println("-------------------------------");
        consumer(new SimpleProxy(new RealObject()));
    }
}

interface Interface {
    void doSomething();

    void somethingElse(String arg);
}

class RealObject implements Interface {

    @Override
    public void doSomething() {
        System.out.println("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("somethingElse " + arg);
    }
}

class SimpleProxy implements Interface {
    private Interface anInterface;

    public SimpleProxy(Interface anInterface) {
        this.anInterface = anInterface;
    }

    @Override
    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        anInterface.doSomething();
    }

    @Override
    public void somethingElse(String arg) {
        System.out.println("SimpleProxy somethingElse");
        anInterface.somethingElse(arg);
    }
}
