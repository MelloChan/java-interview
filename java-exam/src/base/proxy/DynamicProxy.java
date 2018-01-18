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
        // 被代理的具体类 采用组合方式传入Handler
        RealObject realObject = new RealObject();
//        consumer(realObject);
        // 统一的代理接口 Interface 通过 Porxy.newProxyInstance 生成代理类获取实例
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{Interface.class},
                new DynamciProxyHandler(realObject)
        );
        /*
        转发 invoke方法调用我们要执行的方法,
        在调用前后加上其他逻辑代码
        (例如安全检查 日志 过滤处理之类的 而invoke方法利用反射调用生成的代理类的方法
        即已经加上了前后逻辑的代理方法 method.invoke(object, args);
         */
        consumer(proxy);
    }
}

class DynamciProxyHandler implements InvocationHandler {
    private Object object;

    public DynamciProxyHandler(Object object) {
        this.object = object;
    }

    /**
     *
     * @param proxy 动态生成的代理类
     * @param method 需要执行的方法
     * @param args 方法参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() + " , method: " + method + " , args: " + args);
        if (args != null) {
            for (Object arg : args) {
                System.out.println(" " + arg);
            }
        }
        Object result=method.invoke(object,args);
        System.out.println("-----End-----");
        return result;
    }
}

