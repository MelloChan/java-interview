### 框架应用  

- servlet  

生命周期:加载->实例化->服务->销毁  
servlet(非线程安全)是单例多线程的.主要方法有init(),service(),destroy();   
init():仅会执行一次的方法,在服务器装载某个servlet时,用来初始化servlet的各部分数据;  
service():接收响应请求,一般不会直接使用该方法,多数是通过继承HttpServlet后重写doGet()与doPost()方法(或者其他HTTP方法),重写service()方法最终也是交给各种HTTP方法,这点查阅HttpServlet类源码会更清楚;    
destroy():也是仅执行一次的方法,在servlet生命结束时调用,负责释放相关资源.  

备注:servlet是SpringMVC的本质,由一个dispatch servlet来统一分发请求(内部有个映射器),个人初学servlet时觉得原生也挺不错呀,后来意识到当需要编写的
接口非常多时,我们就需要为每个接口重新建一个类,这就导致类规模剧增,复杂度也上升了,而使用框架就很好的解决了这点,扩展性也提高了.实质上,在进行微信开发的时候(当时还未接触框架),就想到且建立一个统一的负责转发请求的总servlet,这种思想
很SpringMVC的思想差不多.

- 转发(forward)与重定向(redirect)  

forward:request.getRequestDispatcher("xxx").forward(request,response);这是一种服务端内部的行为,因此仅触发一次请求与响应,客户端的URL地址栏不会产生变化;

redirect:response.sendRedirect(“xxx”);通知客户端发出新请求去访问响应的页面,因此这是触发二次请求,URL地址栏会相应变化.  

SpringMVC转发与重定向的方法参考Spring实战.  

- bean的加载过程   
 
 

- Spring IoC & AOP  

IoC:控制反转,依赖注入(DI.构造器注入,方法注入),由spring来统一管理各类bean(比如service业务类)的生命周期,开发者不需要自行new,只需通过注解(XML配置/JavaConfig)来获取bean实例,优点在于解耦.

AOP:面向切面编程,一个完整的程序少不了诸如日志/安全/事务管理之类的职责,这些可能与核心业务功能无关,但却贯穿整个系统.这些重复代码的编写显然是不符合设计原则的,且带来了额外的复杂性.AOP解决了这点,其主要是在运行时动态将额外职责代码切入到我们指定的类方法(注解声明).原理就是动态代理(或CGLIB,委托类非接口时),AOP组件简化了这个过程.

- 注入方式  
  
方法注入:  
构造器注入:  

- 事务管理 声明式事务 事务传播   
  
事务拥有[ACID](https://github.com/MelloChan/java-interview/blob/master/content/DB.md)四个特性.特性之间是相互保证的.  
比起编程式事务,Spring的声明式事务(AOP原理)不需要在业务代码中夹杂事务管理的代码(解耦).一般使用注解方法级别的事务管理,更加灵活方便......    

传播行为:  
隔离级别:  

- SpringMVC原理  

- SpringMVC常用注解  

- ORM框架  

hibernate:  
mybatis:    

- 缓存  

- 批量提交  

- SpringBoot  

- RESTFul