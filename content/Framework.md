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
比起编程式事务,Spring的声明式事务(AOP原理)不需要在业务代码中夹杂事务管理的代码(解耦).一般使用注解方法级别的事务管理,更加灵活方便.    

事务[隔离级别](https://github.com/MelloChan/java-interview/blob/master/content/DB.md):    
TransactionDefinition.ISOLATION_DEFAULT:使用底层数据库的默认隔离级别,MySQL是可重复读,Oracle则是提交读;  

TransactionDefinition.ISOLATION_READ_UNCOMMITTED:未提交读,不能防止脏读、不可重复读、幻读等.一般不会用这个隔离级别;    

TransactionDefinition.ISOLATION_READ_COMMITTED:可提交读,能避免脏读,不能防止不可重复读、幻读等;    

TransactionDefinition.ISOLATION_REPEATABLE_READ:可重复读,能避免脏读、不可重复读,不能防止幻读等;    

TransactionDefinition.ISOLATION_SERIALIZABLE:串行化,能避免各类并发问题.但性能差,一般也不会用.  

事务传播行为:    
Propagation.REQUIRED:代表当前方法支持当前事务,与调用者处于同一事务上下文,出错统一回滚(前提是调用者有事务).调用者没有事务或没有被调用则自己创建事务.是默认行为;      

Propagation.SUPPORTS:代表当前方法支持当前事务,与调用者处于同一事务上下文,出错统一回滚(前提是调用者有事务).调用者没有事务或没有被调用则处于非事务下执行;      

Propagation.MANDATORY:代表当前方法支持当前事务,与调用者处于同一事务上下文,出错统一回滚(前提是调用者有事务).调用者没有事务或没有被调用则抛出异常;       

Propagation.REQUIRES.NEW:会自行创建一个事务上下文,调用者有事务则挂起调用者的事务(即出错情况下各自回滚,互不相干);      

Propagation.NOT_SUPPORTED:不会创建事务,方法会以非事务状态执行.调用者有事务则挂起调用者的事务;        

Propagation.NEVER:不会创建事务,方法会以非事务状态执行.调用者有事务则抛出异常;      

Propagation.NESTED:如果当前上下文存在事务,则以嵌套事务执行该方法(嵌套事务:若调用者出错即使自行没有异常也会随调用者回滚,自己出错不影响调用者,自行回滚).调用者没有事务或没有被调用则自行创建事务.          

- SpringMVC流程    

![流程图](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/SpringMVC%E6%B5%81%E7%A8%8B%E5%9B%BE.png)  

DispatcherServlet:一个前端控制器.用户的所有请求都统一交由其处理,是整个MVC流程的中心,由它调用其他系统组件来处理用户请求.因此降低了组件的耦合度;    

HandlerMapping:映射处理器.通过用户请求的URL(具体点可以说是URI,资源标识符)来映射相应的控制器;      

HandlerExecutionChain:封装了拦截器与控制器;    

Handler:即我们编写的控制器;    

HandlerAdapter:根据控制器的类型会生成合适的适配器,到这里才真正去调用控制器(反射机制)的方法处理具体的业务;    

ViewResovler:视图解析器,根据逻辑视图名生成真正的视图,并渲染视图;    

View:即我们编写的一些渲染模板.    

步骤说明:    
①客户端发送请求到达统一处理servlet(DispatcherServlet);      

②根据URL拿到资源标识符(URI),调用处理器映射器获取控制器信息;    

③封装拦截器(如果有)与控制器对象生成HandlerExecutionChain;    

④DispathcerServlet根据控制器类型生成相应的控制器适配器(成功的话会调用拦截器的preHandler方法);    

⑤适配器调用控制器相应方法处理业务;    

⑥DispathcerServlet得到处理后的ModelAndView(之后调用拦截器的postHandler方法);    

⑦DispatcherServlet将ModelAndView传给视图解析器,视图解析器根据逻辑视图名称生成真正的视图View,之后通过模型渲染视图(之后调用拦截器的afterCompletion方法);    

⑧DispatcherServlet将渲染视图结果返回客户端.

- SpringMVC常用注解  

- ORM框架  

- 缓存  

- 批量提交  

- SpringBoot  

SpringBoot不是什么新框架,只是统合了Spring、SpringMVC系列.  
1.他的原则是"约定大于配置",减少开发者在资源配置方面的操作,让一些通用的配置改成默认,我们可以使用JavaConfig与(properties)yml来进行自定义配置,去XML;  

2.他的starter包集合了通用兼容的jar依赖,这减少了我们在pom文件中需要输入大量依赖jar的工作;     

3.Maven作为默认的项目构建方式(也可用Gradle),内嵌tomcat,鼓励使用jar形式部署项目;    

4.进阶学习-> SpringCloud.  

另外,现今的版本已升级为2.0,新增了大量特性,同时只支持JDK8,理论支持JDK9.  
更多查阅官方文档:https://spring.io/guides


- RESTful  

表现层(资源)状态转化,接口URL有固定的格式,如: api.example.com/v1/xxx   其中象征资源的URI只允许名词,对资源的CRUD操作对应着HTTP的请求方法.
例如,GET->获取资源、POST->新增资源、PUT->更新资源、PATCH->局部更新、DELETE->删除资源.   

详细可参考阮一峰的博客:http://www.ruanyifeng.com/blog/2011/09/restful.html  http://www.ruanyifeng.com/blog/2014/05/restful_api.html      

实用的REST框架:[RESTEasy](http://resteasy.jboss.org/),是一款JBoss开源的RESTful Web Services框架.  

- RPC  

远程过程调用,用于解决不同服务器间的通信问题.Java中有所谓的RMI,但有较大的局限性,对服务提供者IP地址+端口的紧密依赖、消费服务两端都必须运行在Java平台.    
需要解决的地方: 通信协议 + (反)序列化.    
流行的RPC框架:  
阿里巴巴 Dubbo：https://github.com/alibaba/dubbo  
新浪微博 Motan：https://github.com/weibocom/motan   
gRPC：https://github.com/grpc/grpc   
rpcx ：https://github.com/smallnest/rpcx   
Apache Thrift ：https://thrift.apache.org/   

