package base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * Created by MelloChan on 2018/1/17.
 */
public class DynamicProxy {
    public static void consumer(Interface i) {
        i.doSomething();
        i.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
//        consumer(realObject);
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamciProxyHandler(realObject)
        );
        consumer(proxy);
    }
}

class DynamciProxyHandler implements InvocationHandler {
    private Object object;

    public DynamciProxyHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + " , method: " + method + " , args: " + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.println(" " + arg);
            }
        }
        return method.invoke(object, args);
    }
}

