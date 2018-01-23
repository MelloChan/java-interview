package java.lang;

/**
 * 编译成功,但永远无法被加载
 * 除非自定义了类加载器强行加载,但也会抛出安全异常,因为以lang开头的包名是不被允许使用的.
 * Created by MelloChan on 2018/1/23.
 */
public class String {
    public static void main(String[] args) {
        System.out.println("ss");
    }
}
/*
 错误: 在类 java.lang.String 中找不到 main 方法, 请将 main 方法定义为:
 public static void main(String[] args)
 否则 JavaFX 应用程序类必须扩展javafx.application.Application
 */
