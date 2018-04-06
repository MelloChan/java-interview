## 前言

### Dubbo是什么  
1.一款分布式服务框架;  
2.高性能和透明化的RCP框架;  
3.SOA服务治理方案.
 
### 架构
![生产者消费者模型](http://dubbo.apache.org/books/dubbo-user-book/sources/images/dubbo-architecture.jpg)  

## Simple Demo  

参考用户手册:http://dubbo.apache.org/books/dubbo-user-book/      
开发者手册:http://dubbo.apache.org/books/dubbo-dev-book/design.html   

框架搭配:Dubbo + Zookeeper + Spring  
构建工具:IDEA + Maven      

#### zookeeper 

首先先下载zookeeper:  https://zookeeper.apache.org/   之后压缩到任意文件夹,在bin目录下命令行输入 sh zkServer.sh start-foreground,开启zookeeper服务.
 
#### 构建项目 
首先,构建一个maven项目,创建相应模块(服务提供者接口sdk,服务提供者provider以及消费者consumer)结构与pom依赖如下:  
![](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/dubbo-learn.png)
```
    <!-- dubbo相关依赖 -->
    <dependency>
      <groupId>com.alibaba</groupId>
      <artifactId>dubbo</artifactId>
      <version>2.5.3</version>
    </dependency> 
    
    <dependency>
      <groupId>log4j</groupId>
      <artifactId>log4j</artifactId>
      <version>1.2.17</version>
    </dependency>
    
    <dependency>
      <groupId>com.github.sgroschupf</groupId>
      <artifactId>zkclient</artifactId>
      <version>0.1</version>
    </dependency>
    
    <!-- spring相关依赖 -->
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>4.2.5.RELEASE</version>
    </dependency>
    
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context-support</artifactId>
      <version>4.2.5.RELEASE</version>
    </dependency>
    
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-core</artifactId>
      <version>4.2.5.RELEASE</version>
    </dependency>
    
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-beans</artifactId>
      <version>4.2.5.RELEASE</version>
    </dependency>
    
```

### sdk  

这个模块用于提供服务接口,该接口需单独打包,在生产者与消费者共享.
```

package com.mello;

public interface UserService {
    String saySomething(String string);
}

```

### provider

服务提供者,实现相应接口.首先在pom下引入sdk依赖:
```

        <dependency>
            <groupId>com.mello</groupId>
            <artifactId>sdk</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        
```  

创建接口实现类:
```

package com.mello;

public class UserServiceImpl implements UserService {

    @Override
    public String saySomething(String string) {
        return string;
    }
}

```

在resources目录下创建provider.xml:  
```

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <!--定义提供方应用信息-->
    <dubbo:application name="dubbo-provider"/>
    <!--zookeeper 暴露服务地址-->
    <dubbo:registry address="zookeeper://127.0.0.1:2181"/>
    <!--端口20880-->
    <dubbo:protocol name="dubbo" port="20880"/>
    <!--声明需要暴露的服务接口-->
    <dubbo:service interface="com.mello.UserService" ref="userService" group="dubbo"/>
    
    <bean id="userService" class="com.mello.UserServiceImpl"/>
</beans>

```

简单测试一下,创建TestProvider类并运行,控制台打印出Hello! :
```

package com.mello;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestProvider {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:provider.xml");
        UserService service= (UserService) context.getBean("userService");
        System.out.println(service.saySomething("Hello!"));
        Thread.currentThread().join();
    }
}/* output:
       省略配置信息......
       Hello!
      */

```  

### consumer

消费者方面同样先引入sdk依赖:  
```

    <dependency>
            <groupId>com.mello</groupId>
            <artifactId>sdk</artifactId>
            <version>1.0-SNAPSHOT</version>
    </dependency>
    
```

resources目录下配置consumer.xml:  
```  

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:dubbo="http://code.alibabatech.com/schema/dubbo"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://code.alibabatech.com/schema/dubbo http://code.alibabatech.com/schema/dubbo/dubbo.xsd">
    <dubbo:application name="dubbo-cusumer"/>
    <dubbo:registry protocol="zookeeper" address="zookeeper://127.0.0.1:2181"/>
    <dubbo:reference id="userService" interface="com.mello.UserService" group="dubbo"/>
</beans>

```

简单测试(需要先运行TestProvider类):  
```

package com.mello;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConsumer {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("classpath:consumer.xml");
        UserService service= (UserService) context.getBean("userService");
        System.out.println(service.saySomething("Hello Dubbo!"));
    }/* output:
          省略log4j日志信息......
          Hello Dubbo!
         */
}

```

经过以上步骤,一个dubbo的最简单的demo就完成了.   

## dubbo-admin  

dubbo-admin,dubbo服务的管理平台.  
直接在https://github.com/apache/incubator-dubbo/releases 下载解压,在dubbo-admin目录下命令行输入 mvn install -D maven.test.skip=true,构建完成后复制target目录下的war到tomact(版本8及以上)的\webapps\ROOT下,然后开启tomcat访问页面即可.  
登录的用户名/密码(root/root)都在dubbo.properties文件里.  







