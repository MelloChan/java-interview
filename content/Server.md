### 服务器  

- Web服务器 & 应用服务器

Web服务器:Apache、Nginx等,一般用来处理HTTP协议请求,只响应静态资源以及进行反向代理、负载均衡等;     
应用服务器:Tomcat、jetty、Jboss等,虽然应用服务器能做到web服务器所负责的事情,但他更适合提供动态内容.  

总结:Web服务器适合用于提供静态内容,充当应用服务器的反向代理,通过某种过滤技术识别动态内容请求并透明的转发到应用服务器,应用服务器或者说Servlet容器其架构模型更适合提供动态内容.  

- Tomcat集群   

业务升级,并发度上升,单台应用服务器处理达到瓶颈,此时服务器肯定支持不下了,会面临单点故障的问题.这时就需要将单业务部署到多台服务器上.
使用Nginx来进行Tomcat集群(横向集群 OR 纵向集群 在单台服务器上部署多个tomcat实例或多台服务器上部署)操作很简单,修改server.xml,让tomcat间的HTTP(HTTPS)端口、远程服务(shutdown)端口、AJP端口各不相同. 
然后在Nginx的配置文件http{},里面配置upstream "集群服务名"{},之后添加proxy_pass与集群服务名字相同保存即可.具体如下:   

![Nginx配置负载均衡](https://raw.githubusercontent.com/MelloChan/java-interview/master/image/Nginx%E8%B4%9F%E8%BD%BD%E5%9D%87%E8%A1%A1.png)  

之后访问http://localhost就能进行默认轮询Tomcat访问了,当然负载均衡的算法很多:  

1.轮询(默认):顺序分配请求;    

2.最少连接:谁连接数最少就将请求分配给谁;  

3.权值:自己根据服务器性能,按比例分配请求;   

4.ip_hash:根据IP哈希分配处理请求的tomcat;  

5.url_hash;根据URL哈希分配处理的tomcat;     

6.fair:按服务器的响应时间分配请求,响应时间越快分配的越多.  
  
扩展问题:集群了,Session或者说用户状态怎么维持?  
使用分布式session框架,redis服务器存储session也是一个选择.    
  
- Nginx Apache  

https://w3techs.com/technologies/overview/web_server/all

- 反向代理 负载均衡  

一般使用Nginx来作为反向代理与负载均衡的Web服务器,其因性能高效(C编写,使用协程多路复用机制处理请求)且轻量而受到欢迎.      

nginx的学习教程:http://tengine.taobao.org/book/index.html   

- 分布式&集群 

简单理解:  

分布式:拆分业务部署到多台服务器;    

集群:防止单点故障,单个业务多实例部署到多台服务器,对外提供相同服务.    

简单科普参考:https://mp.weixin.qq.com/s/HbYfF4iBGgc7VHPFr5qJoA  

拓展微服务:https://www.ibm.com/developerworks/community/blogs/3302cc3b-074e-44da-90b1-5055f1dc0d9c/entry/%E8%A7%A3%E6%9E%90%E5%BE%AE%E6%9C%8D%E5%8A%A1%E6%9E%B6%E6%9E%84_%E4%B8%80_%E4%BB%80%E4%B9%88%E6%98%AF%E5%BE%AE%E6%9C%8D%E5%8A%A1?lang=en