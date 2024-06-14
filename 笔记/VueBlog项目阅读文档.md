# 一、Shiro

## 概述

Apache shiro是一个功能强大且易于使用的Java安全（权限）框架。Shiro可以完成：认证、授权、加密、会话管理、与web集成、缓存等。借助Shiro可以快速轻松的保护任何应用程序——从最小的移动应用到最大的web应用和企业应用程序。

## 主要功能

三个核心组件：Subject，SecurityManager和Realms

<img src="https://img-blog.csdnimg.cn/img_convert/706a31d50a7a7ca01a78da4817de8ccf.png"  />

1. **Subject**：即“当前操作用户”。但是，在Shiro中，Subject这一概念并不仅仅指人，也可以是**第三方进程、后台帐户（Daemon Account）或其他类似事物**。它仅仅意味着“当前跟软件交互的东西”。Subject代表了当前用户的安全操作，SecurityManager则管理所有用户的安全操作。
2. **SecurityManager**：它是[Shiro框架](https://so.csdn.net/so/search?q=Shiro框架&spm=1001.2101.3001.7020)的核心，典型的[Facade模式](https://www.runoob.com/design-pattern/facade-pattern.html) ，Shiro通过SecurityManager来管理内部组件实例，并通过它来提供安全管理的各种服务。
3. **Realm**：
           Realm充当了Shiro与应用安全数据间的“桥梁”或者“连通器”。也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息。
           从这个意义上讲，Realm实质上是一个安全相关的DAO：它封装了数据源的连接细节，并再需要时将相关数据提供给Shiro。当配置Shiro时，你必须至少指定一个Realm，用于认证或周全。配置多个Realm是可以的，但是至少需要一个。
          Shiro内置了可以连接大量安全数据源（又名目录）的Realm，如LDAP、关系数据库（JDBC）、类似INI的文本配置资源以及属性文件等。如果缺省的Realm不能满足需求，你还可以插入代表自定义数据源的自己的Realm实现。 

## 基本功能

<img src="https://img-blog.csdnimg.cn/img_convert/4be541ef2b5b188c8b20b59a6cd2fe8e.png"  />

- **Authentication**：身份认证/登录，验证用户是不是相应的身份；

- **Authorization**：授权，即权限验证，验证某个已认证的用户是否拥有某个功能；判断用户是否能做事情，常见的如：验证某个用户是否拥有某个角色。或者细粒度的言则会那个某个用户对某个资源是否拥有某个权限。

- **Session Management**：会话管理，即用户登录后就是一次会话，在没有退出之前，它的所有信息都在会话中；会话可以是简单JavaSE框架的，也可以是web环境的；

[^什么是会话]: 会话Session代表的是客户端和服务器的一次交互过程，这个过程可以是连续的也可以是时断时续的。在Servlet中（jsp），一旦用户与服务端交互，服务器tomcat就会为用户创建一个Session，同时前端会有一份jssessionid，每次交互都会携带。如此一来，服务器只要在接到用户请求时候，就可以拿到jssessioni并根据这个ID在内存内中找到对应的会话Session，当拿到session会话后，那么我们iu可以操作会话了。会话存活期间，我们就能认为用户一直处于正在使用着网站的状态，一旦session超期过时，那么就可以认为用户已经离开网站，停止交互了。用户的身份信息，我们也是通过session来判断的，在session中可以保存不同用户的信息。

知识点补充：[会话](https://blog.csdn.net/qq_38446413/article/details/105755620)

- **Cryptography**：加密，保护数据的安全性，如密码加密存储到数据库，而不是明文存储；

- **Web Support**：Web 支持，可以非常容易的集成到 Web 环境；

- **Caching**：缓存，比如用户登录后，其用户信息、拥有的角色 / 权限不必每次去查，这样可以提高效率；

- **Concurrency**：shiro 支持多线程应用的并发验证，即如在一个线程中开启另一个线程，能把权限自动传播过去；

- **Testing**：提供测试支持；

- **Run As**：允许一个用户假装为另一个用户（如果他们允许）的身份进行访问；

- **Remember Me**：记住我，这个是非常常见的功能，即一次登录后，下次再来的话不用登录了

## 二、Shiro与Spring Security的区别

1. Shiro比Spring Security更容易使用，也就是实现上简单一点，同时基本的授权认证Shiro也基本够用
2. Spring Security社区支持度更高（但是安装Spring Security很难），Spring社区支持力度和更新维护上有优势，同时Spring这一套的结合较好
3. Shiro功能强大，且灵活、简单，是apache下比较可靠的项目，且不跟任何框架或者容器绑定，可以独立运行

## 三、Spring Boot集成Shiro

### 环境配置

1. 创建一个普通的SpringBoot的Web工程
2. 在pom.xml中添加Shiro依赖

```java
<!-- Shiro 依赖 -->
        <dependency>
            <groupId>org.apache.shiro</groupId>
            <artifactId>shiro-spring</artifactId>
            <version>1.8.0</version>
        </dependency>
```

### 配置Shiro

1. 自定义MyRealm类

   ```Java
   /**
    * 自定义Realm，用来实现用户的认证和授权
    * Realm：父类抽象类
    */
   public class MyRealm implements Realm {
    
       @Override
       public String getName() {
           return null;
       }
    
       @Override
       public boolean supports(AuthenticationToken authenticationToken) {
           return false;
       }
    
       @Override
       public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
           return null;
       }
   }
   ```

   

2. 定义配置类ShiroConfig

   ```Java
   /**
    * /当前类是一个Spring的配置类，用于模拟Spring的配置文件
    *
    * @Configuration： 表示当前类作为配置文件使用（配置容器，放在类的上面；等同 xml 配置文件，可以在其中声明 bean ）
    * @Bean： 将对象注入到Spring容器中（类似<bean>标签，放在方法上面）；不指定对象的名称，默认是方法名是 id
    */
   @Configuration
   public class ShiroConfig {
       /**
        * 1、Subject：
        * 即“当前操作用户”。它仅仅意味着“当前跟软件交互的东西”。
        * Subject代表了当前用户的安全操作，SecurityManager则管理所有用户的安全操作。
        *
        * 2、SecurityManager：
        * 它是Shiro框架的核心，典型的Facade模式，Shiro通过SecurityManager来管理内部组件实例，并通过它来提供安全管理的各种服务。
        *
        * 3、Realm：
        * Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。当对用户执行认证（登录）和授权（访问控制）验证时，Shiro会从应用配置的Realm中查找用户及其权限信息。
        */
    
       /**
        * 配置Shiro的安全管理器
        * @Bean： 将对象注入到Spring容器中（类似<bean>标签，放在方法上面）；不指定对象的名称，默认是方法名是 id
        */
       @Bean
       public SecurityManager securityManager(Realm myRealm){
           DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
           //设置一个Realm，这个Realm是最终用于完成我们的认证号和授权操作的具体对象
           securityManager.setRealm(myRealm);
           return securityManager;
       }
    
       /**
        * 配置一个自定义的Realm的bean，最终将使用这个bean返回的对象来完成我们的认证和授权
        */
       @Bean
       public MyRealm myRealm(){
           MyRealm myRealm = new MyRealm();
           return myRealm;
       }
    
       /**
        * 配置一个Shiro的过滤器bean，这个bean将配置Shiro相关的一个规则的拦截
        * 如什么样的请求可以访问，什么样的请求不可以访问等等
        */
       @Bean
       public ShiroFilterFactoryBean  shiroFilterFactoryBean(SecurityManager securityManager){
           //创建Shiro的拦截的拦截器 ，用于拦截我们的用户请求
           ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    
           //设置Shiro的安全管理，设置管理的同时也会指定某个Realm 用来完成我们权限分配
           shiroFilter.setSecurityManager(securityManager);
           //用于设置一个登录的请求地址，这个地址可以是一个html或jsp的访问路径，也可以是一个控制器的路径
           //作用是用于通知Shiro我们可以使用这里路径转向到登录页面，但Shiro判断到我们当前的用户没有登录时就会自动转换到这个路径
           //要求用户完成成功
           shiroFilter.setLoginUrl("/");
           //登录成功后转向页面，由于用户的登录后期需要交给Shiro完成，因此就需要通知Shiro登录成功之后返回到那个位置
           shiroFilter.setSuccessUrl("/success");
           //用于指定没有权限的页面，当用户访问某个功能是如果Shiro判断这个用户没有对应的操作权限，那么Shiro就会将请求
           //转向到这个位置，用于提示用户没有操作权限
           shiroFilter.setUnauthorizedUrl("/noPermission");
    
           //定义一个Map集合，这个Map集合中存放的数据全部都是规则，用于设置通知Shiro什么样的请求可以访问,什么样的请求不可以访问
           Map<String,String> filterChainMap = new LinkedHashMap<String,String>();
    
           // /login 表示某个请求的名字；anon 表示可以使用游客进行登录（这个请求不需要登录）
           filterChainMap.put("/login","anon");
    
           //我们可以在这里配置所有的权限规则，这列数据需要从数据库中读取出来
    
           //或者在控制器中添加Shiro的注解
           /**
            /admin/**  表示一个请求名字的通配， 以admin开头的任意子路径下的所有请求
            authc 表示这个请求需要进行认证（登录），只有认证（登录）通过才能访问
            注：
               ** 表示任意子路径
               *  表示任意的一个路径
               ? 表示 任意的一个字符
            */
           filterChainMap.put("/admin/**","authc");
           filterChainMap.put("/user/**","authc");
    
           //表示所有的请求路径全部都需要被拦截登录，这个必须必须写在Map集合的最后面,这个选项是可选的
           //如果没有指定/** 那么如果某个请求不符合上面的拦截规则Shiro将方行这个请求
           filterChainMap.put("/**","authc");
    
           shiroFilter.setFilterChainDefinitionMap(filterChainMap);
    
           return shiroFilter;
       }
   }	
   ```

   

3. 定义UserController类

   ```Java
   @Controller
   public class UserController {
       @RequestMapping("/")
       public String index(){
           return "login";
       }
    
       @RequestMapping("/login")
       public String login(String username, String password, Model model){
           return "redirect:/success";
       }
    
       @RequestMapping("/success")
       public String success(){
           return "success";
       }
    
       @RequestMapping("/noPermission")
       public String noPermission(){
           return "noPermission";
       }
    
       @RequestMapping("/user/test")
       public @ResponseBody
       String userTest(){
           return "这是userTest请求";
       }
    
       @RequestMapping("/admin/test")
       public @ResponseBody String adminTest(){
           return "这是adminTest请求";
       }
    
       @RequestMapping("/admin/add")
       public @ResponseBody String adminAdd(){
           Subject subject= SecurityUtils.getSubject();
           return "这是adminAdd请求";
       }
   }
   ```

### 配置Shiro认证账号

1. 自定义MyAuthenticatingRealm类

   ```Java
   /**
    * 自定义Realm，用来实现用户的认证和授权
    * AuthenticatingRealm 只负责认证（登录）的Realm父类
    */
   public class MyAuthenticatingRealm extends AuthenticatingRealm {
       /**
        * Shiro的认证方法我们需要在这个方法中来获取用户的信息（从数据库中）
        * @param authenticationToken   用户登录时的Token（令牌），这个对象中将存放着我们用户在浏览器中存放的账号和密码
        * @return 返回一个AuthenticationInfo 对象，这个返回以后Shiro会调用这个对象中的一些方法来完成对密码的验证 密码是由Shiro进行验证是否合法
        * @throws AuthenticationException   如果认证失败，Shiro就会抛出AuthenticationException 也可以手动抛出这个AuthenticationException
        *      以及它的任意子异常类，不同的异常类型对应认证过程中的不同错误情况，我们需要根据异常类型来为用户返回特定的响应数据
        * AuthenticationException  异常的子类  可以自己抛出
        *      AccountException  账号异常  可以自己抛出
        *      UnknownAccountException  账号不存在的异常  可以自己抛出
        *      LockedAccountException   账号异常锁定异常  可以自己抛出
        *      IncorrectCredentialsException  密码错误异常（这个异常会在Shiro进行密码验证时抛出）
        */
       @Override
       protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
           //将AuthenticationToken强转成UsernamePasswordToken 这样获取账号和密码更加的方便
           UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
    
           //获取用户在浏览器中输入的账号
           String userName = token.getUsername();
    
           //认证账号,正常情况我们需要这里从数据库中获取账号的信息，以及其他关键数据，例如账号是否被冻结等等
           String dbUserName = userName;
    
           if(!"admin".equals(dbUserName) && !"zhangsan".equals(dbUserName)){//判断用户账号是否正确
               throw  new UnknownAccountException("账号错误");
           }
           if("zhangsan".equals(userName)){
               throw  new LockedAccountException("账号被锁定");
           }
           //定义一个密码(这个密码应该来自数据库)
           String dbpassword = "123456";
    
           /**
            * 创建密码认证对象，由Shiro自动认证密码
            * 参数1  数据库中的账号（页面账号也可）
            * 参数2  数据库中的密码
            * 参数3  当前Relam的名字
            * 如果密码认证成功，会返回一个用户身份对象；如果密码验证失败则抛出异常
            */
           //认证密码是否正确
           return new SimpleAuthenticationInfo(dbUserName,dbpassword,getName());
       }
   }
   ```

   

2. 修改UserController类

   ```Java
   @RequestMapping("/login")
       public String login(String username, String password, Model model){
           //创建一个shiro的Subject对象，利用这个对象来完成用户的登录认证
           Subject subject = SecurityUtils.getSubject();
    
           //判断当前用户是否已经认证过，如果已经认证过着不需要认证;如果没有认证过则完成认证
           if(!subject.isAuthenticated()){
               //创建一个用户账号和密码的Token对象，并设置用户输入的账号和密码
               //这个对象将在Shiro中被获取
               UsernamePasswordToken token = new UsernamePasswordToken(username,password);
               try {
                   //如账号不存在或密码错误等等，需要根据不同的异常类型来判断用户的登录状态，并给予友好的信息提示
                   //调用login后，Shiro就会自动执行自定义的Realm中的认证方法
                   subject.login(token);
               } catch (UnknownAccountException e) {
                   //表示用户的账号错误，这个异常是在后台抛出
                   System.out.println("---------------账号不存在");
                   model.addAttribute("errorMessage","账号不存在");
                   return "login";
               }catch (LockedAccountException e){
                   //表示用户的账号被锁定，这个异常是在后台抛出
                   System.out.println("===============账号被锁定");
                   model.addAttribute("errorMessage","账号被冻结");
                   return "login";
               }catch (IncorrectCredentialsException e){
                   //表示用户的密码错误，这个异常是shiro在认证密码时抛出
                   System.out.println("***************密码不匹配");
                   model.addAttribute("errorMessage","密码错误");
                   return "login";
               }
           }
           return "redirect:/success";
       }
   ```

   [^问题]: 暂时不清楚UserController中的login方法如何调用MyAuthenticatingRealm中的账号验证逻辑，猜测是在subject.login(token)处进行了调用
   [^回答]: 

   

### 认证缓存

当登录成功一次以后，我们点击后退，此时输入账号和密码，无论输入什么信息，即使什么都不输入直接点击“登录”按钮，Shiro都会认为登录成功，这是因为Shiro在登录成功后会将数据写入Shiro的缓存导致的，因此应该在登录请求的控制器判断是否已经认证前，添加一个登出操作，以清空缓存，这样就可以重复测试登录了

修改UserController类

```Java
/创建一个shiro的Subject对象，利用这个对象来完成用户的登录认证
Subject subject= SecurityUtils.getSubject();
 
//登出方法调用，用于清空登录时的缓存信息，否则无法重复登录
subject.logout();
```

[^问题]: 此处的logout()应该添加到哪个位置，如果添加到return之前、if之后，是否会导致验证成功后直接登出？实际应用时可能不会添加在UserController类中
[^回答]: 方法添加在获取subject对象之后，if判断之前，用于再登录时清空（已登录的）缓存信息，以便再次进行登录验证

### 密码加密

[加盐算法](https://blog.csdn.net/m0_60354608/article/details/130112235)

### 权限分配

1. 修改MyRealm类继承父类为AuthorizingRealm类，并实现抽象方法doGetAuthorizationInfo

   ```Java
   /**
    * 自定义Realm，用来实现用户的认证和授权
    * AuthenticatingRealm 只负责认证（登录）的Realm父类
    * AuthorizingRealm    负责认证（登录）和授权 的Realm父类
    */
   public class MyAuthRealm extends AuthorizingRealm {
    
       /**
        * AuthorizingRealm
        * Shiro用户授权的回调方法
        * @param principalCollection
        * @return
        */
       protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
           //从Shiro中获取用户名
           Object username = principalCollection.getPrimaryPrincipal();
           //创建一个SimpleAuthorizationInfo类的对象，利用这个对象需要设置当前用户的权限信息
           SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
    
           //创建角色信息的集合
           Set<String> roles = new HashSet<String>();
    
           //根据账号到数据库中获取用户所对应的所有角色信息,并初始化到roles集合中
           if("admin".equals(username)){
               roles.add("admin");
               roles.add("user");
           }else if ("zhangsan".equals(username)){
               roles.add("user");
           }
           Set<String>psermission = new HashSet<String>();
           if("admin".equals(username)){
               psermission.add("admin:add");
           }
           //设置角色信息
           simpleAuthorizationInfo.setRoles(roles);
           simpleAuthorizationInfo.setStringPermissions(psermission);
           return simpleAuthorizationInfo;
       }
   }
   ```

2. 修改ShiroConfiig

   ```Java
       /**
        * 配置一个Shiro的过滤器bean，这个bean将配置Shiro相关的一个规则的拦截
        * 如什么样的请求可以访问，什么样的请求不可以访问等等
        */
       @Bean
       public ShiroFilterFactoryBean  shiroFilterFactoryBean(SecurityManager securityManager){
           //创建Shiro的拦截的拦截器 ，用于拦截我们的用户请求
           ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
    
           //设置Shiro的安全管理，设置管理的同时也会指定某个Realm 用来完成我们权限分配
           shiroFilter.setSecurityManager(securityManager);
           //用于设置一个登录的请求地址，这个地址可以是一个html或jsp的访问路径，也可以是一个控制器的路径
           //作用是用于通知Shiro我们可以使用这里路径转向到登录页面，但Shiro判断到我们当前的用户没有登录时就会自动转换到这个路径
           //要求用户完成成功
           shiroFilter.setLoginUrl("/");
           //登录成功后转向页面，由于用户的登录后期需要交给Shiro完成，因此就需要通知Shiro登录成功之后返回到那个位置
           shiroFilter.setSuccessUrl("/success");
           //用于指定没有权限的页面，当用户访问某个功能是如果Shiro判断这个用户没有对应的操作权限，那么Shiro就会将请求
           //转向到这个位置，用于提示用户没有操作权限
           shiroFilter.setUnauthorizedUrl("/noPermission");
    
           //定义一个Map集合，这个Map集合中存放的数据全部都是规则，用于设置通知Shiro什么样的请求可以访问,什么样的请求不可以访问
           Map<String,String> filterChainMap = new LinkedHashMap<String,String>();
    
           // /login 表示某个请求的名字；anon 表示可以使用游客进行登录（这个请求不需要登录）
           filterChainMap.put("/login","anon");
    
           //我们可以在这里配置所有的权限规则，这列数据需要从数据库中读取出来
    
           //或者在控制器中添加Shiro的注解
           /**
            1、 /admin/**  表示一个请求名字的通配， 以admin开头的任意子路径下的所有请求
            authc 表示这个请求需要进行认证（登录），只有认证（登录）通过才能访问
            注：
               ** 表示任意子路径
               *  表示任意的一个路径
               ? 表示 任意的一个字符
            2、roles[admin] 表示 以/admin/**开头的请求需要拥有admin角色才可以访问否   则返回没有权限的页面
               perms[admin:add] 表示 /admin/test的请求需要拥有 admin:add权限才可访问
            注：admin:add仅仅是一个普通的字符串用于标记某个权限功能
            */
           filterChainMap.put("/admin/test","authc,perms[admin:add]");
           filterChainMap.put("/admin/**","authc,roles[admin]");
           filterChainMap.put("/user/**","authc,roles[user]");
    
           //表示所有的请求路径全部都需要被拦截登录，这个必须必须写在Map集合的最后面,这个选项是可选的
           //如果没有指定/** 那么如果某个请求不符合上面的拦截规则Shiro将方行这个请求
           //filterChainMap.put("/**","authc");
    
           shiroFilter.setFilterChainDefinitionMap(filterChainMap);
    
           return shiroFilter;
       }
   }
   ```

   [^问题]: 此处filterChainMap.put()的第二个参数的含义？如何处理传输的字符串？

   用户拥有角色和权限之后需要配置Shiro的权限规则

### 基于注解的权限控制

1. 修改ShiroConfig，添加

   ```Java
   /**
        * 开启Shiro注解支持（例如@RequiresRoles()和@RequiresPermissions()）
        * shiro的注解需要借助Spring的AOP来实现
        * @return
        */
       @Bean
       public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
           DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator=new DefaultAdvisorAutoProxyCreator();
           advisorAutoProxyCreator.setProxyTargetClass(true);
           return advisorAutoProxyCreator;
       }
    
       /**
        * 开启AOP的支持
        * @return
        */
       @Bean
       public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
           AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor=new AuthorizationAttributeSourceAdvisor();
           authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
           return authorizationAttributeSourceAdvisor;
       }
   ```

   [^什么是AOP]: AOP 的作用就是保证开发者在不修改源代码的前提下，为系统中的业务组件添加某种通用功能。

2. 修改UserController

   ```Java
   @RequiresRoles(value = {"user"})
       @RequestMapping("/user/test")
       public @ResponseBody String userTest(){
           return "这个userTest请求";
       }
    
       //RequiresRoles  Shiro的注解 表示访问这功能必须要拥有 admin角色
       //注：如果需要支持多个角色，就直接填写多个角色名称即可；如 "admin","user"
       //RequiresRoles 属性 logical 用于在拥有多个角色时使用 取值为Logical.AND 表示并且的意思必须同时拥有多个角色 或
       //               Logical.OR 或者的意思，只要拥有多个角色中的其中一个即可
       //注：使用了注解以后需要配置Spring声明式异常捕获，否则将在浏览器中直接看到Shiro的错误信息而不是友好的信息提示
       @RequiresRoles(value = {"admin"})
       @RequestMapping("/admin/test")
       public @ResponseBody String adminTest(){
           return "这个adminTest请求";
       }
    
       //@RequiresPermissions 注解用于指定当前请求必须要拥有指定的权限名字为 admin:add才能访问
       //admin:add 只是一个普通的权限名称字符串，表示admin下的add功能
       @RequiresPermissions(value = {"admin:add"})
       @RequestMapping("/admin/add")
       public @ResponseBody String adminAdd(){
           Subject subject = SecurityUtils.getSubject();
           //验证当前用户是否拥有这个权限
           //subject.checkPermission();
           //验证当前用户是否拥有这个角色
           //subject.checkRole();
           return "这个adminAdd请求";
       }
    
       //配置一个Spring的异常监控，当工程抛出了value所指定的所以异常类型以后将直接进入到当前方法中
       @ExceptionHandler(value = {Exception.class})
       public String myError(Throwable throwable){
           //获取异常的类型，应该根据不同的异常类型进入到不通的页面显示不同提示信息
           System.out.println(throwable.getClass());
           System.out.println("---------------------------------");
           return "noPermission";
       }
   ```

   注：Shiro验证失败以后会抛出异常，因此这时必须要配置一个Spring的异常监控方法myError否则当前Shiro权限认证失败以后将无法转向到错误页面

### Shiro标签

[Shiro标签](https://blog.csdn.net/MinggeQingchun/article/details/126414384)



# springboot的架构

[什么是Dao层、Entity层、Service层、Servlet层、Utils层？-CSDN博客](https://blog.csdn.net/Restarting2019/article/details/122296373)

## DAO层

DAO（Data Access Object）模型就是写一个类，把访问数据库的代码封装起来，DAO在数据库与业务逻辑之间。

DAO是数据访问层，DAO的作用是封装对数据库的访问：增删改查，不涉及业务逻辑，只是达到按某个条件获得指定数据的要求



# Mybatis

## 简介

Mybatis是一款优秀发持久层框架，它支持自定义SQL、存储过程以及高级映射。Mybatis免除了几乎所有的JDBC代码以及设置参数和获取结构集的工作。Mybatis可以通过简单的XML或注解来配置和映射原始类型、接口和Java POJO （Plain Old Java Object，普通老式Java对象）为数据库中的记录。

## 入门

要使用Mybatis，只需将mybatis-x.x.x.jar文件置于类路径（classpath）中即可。如果使用Maven来构建项目，则需要将下面的代码置于pom.xml文件中。

```Java
<dependency>
  <groupId>org.mybatis</groupId>
  <artifactId>mybatis</artifactId>
  <version>x.x.x</version>
</dependency>
```

Maven就是一套用来管理项目代码的框架。

[^SqlSessionFactroy是什么？有什么作用？]: 每个基于 MyBatis 的应用都是以一个 SqlSessionFactory 的实例为核心的，SqlSessionFactroy与SqlSession是同一个接口，SqlSessionFactroy是通过build方法创建的，而build方法创建的是一个SqlSessionFactroy的实现类，叫DefaultSqlSessionFactroy，该实现类主要用的设计模式是建造者（build）模式，其最终目的是为了创建出一个SqlSession接口的实现类——DefaultSqlSession。          这个实现类可以将进行增删改查以及事务操作等。DefaultSqlSession底层调用Executor执行器来执行对应操作，而Executor本质上也是一个接口，它又调用了它父接口的方法来实现数据库操作。简单来说，SqlSessionFactroy就是一个继承底层的封装了SQL语句的接口的最终接口，所以本质上还是通过SQL语句来实现数据库操控，封装成接口就是为了方便使用。
[^什么是建造者（build）模式？]: 在开发中，有时候我们需要创建一个复杂的对象，这个对象的创建有一个固定的步骤，并且每个步骤中会涉及到多个组件对象，这个时候就可以考虑建造者模式。使用建造者模式将原本复杂的对象创建过程按照规律将其分解成多个小步骤，这样在构建对象时可以灵活的选择或修改步骤。建造者模式将对象的创建和表示过程进行分离，这样我们可以使用同样的过程，只需修改这个过程中的小步骤，便能够构建出不同的对象。而对于调用方来说，我们只需要传入需要构建的类型，便能够得到需要的对象，并不需要关心创建的过程，从而实现解耦。

[建造者模式](https://blog.csdn.net/qq_38550836/article/details/125862850)

# Lombok

[Lombok简介、使用、工作原理、优缺点-CSDN博客](https://blog.csdn.net/ThinkWon/article/details/101392808)

### 简介

Lombok是一个Java库，能自动插入编辑器并构建工具，简化Java开发。通过**添加注解**的方式，不需要为类编写getter或equals方法，同时自动化日志变量。

## 使用

|           注解           | 作用                                                         |
| :----------------------: | ------------------------------------------------------------ |
|          @Data           | 注解在类，生成setter/getter、equals、canEqual、hashCode、toString方法，如为final属性，则不会为该属性生成setter方法。 |
|          @Slf4j          | 注解在类，如为Controller添加该注解，可以自动化日志变量（严格意义来说是常量，final修饰） |
|         @Setter          | 注解在类或字段，注解在类时为所有字段生长setter方法，注解在字段时只为该字段生成setter方法 |
|         @Getter          | 使用方法同上                                                 |
|        @ToString         | 注解在类，添加toString方法                                   |
|    @EqualAndHashCode     | 注解在类，生成hashcode和equals方法                           |
| @RequiredArgsConstructor | 注解在类，为类中需要特殊处理的字段生成构造方法，比如final和被@NonNull注解的字段 |
|   @AllArgsConstructor    | 注解在类，生成包含类中所有字段的构造方法                     |

**日志**：参考idea报错时的控制台信息。日志的作用时记录系统运行过程及异常信息，为快速定位系统运行中出现的问题及开发过程中的程序调试问题提供详细信息。日志的作用可以概括为如下几个方面：

- 故障排查
- 数据分析
- 记录用户操作的审计日志
- 快速定位问题根源
- 追踪程序执行的过程

[日志组件SLF4J](https://blog.csdn.net/foreverling/article/details/51385128)

## Lombok的优缺点

### 优点

1. 能通过注解的形式自动生成构造器、getter/setter、equals、hashcode、toString等方法，一定程度上提高了开发效率
2. 让代码变得简洁，不用过多的关注相应的方法
3. 属性做修改时，也简化了维护为这些属性添加的getter/setter方法等

### 缺点

1. 不支持多种参数构造器的重载
2. 虽然省去了手动创建getter/setter方法的麻烦，但大大降低了代码的可读性和完整性

# Redis

## 简介

Redis（Remote Dictionary Server）是一个开源的内存数据库，遵循BSD协议，它提供了一个高性能的键值（key-value）存储系统，常用于缓存、消息队列、会话存储等应用场景。

### 特点：

- **性能极高**：Redis以其极高的性能著称，能够支持每秒数十万次的读写操作。这使得Redis成为处理高并发请求的理想选择，尤其是在快速响应的应用场景中，如缓存、会话管理、排行榜等
- **丰富的数据类型**：Redis不仅支持基本的键值存储，还提供了丰富的数据类型，包括字符串、列表、集合、哈希表、有序集合等。这些数据类型为开发者提供了灵活的数据操控能力，是的Redis可以适应各种不同的应用场景。
- **原子性操作**：Redis的所有操作都是原子性的，这意味着操作要么完全执行，要么完全不执行。这种特性对于确保数据的一致性和完整性至关重要，尤其是在高并发环境下处理事务时。
  [原子性](https://www.cnblogs.com/yeyang/p/13576636.html)
- **持久化**：Redis支持数据的持久化，可以将内存中的数据保存到磁盘中，以便系统重启后恢复数据。这为Redis提供了数据安全性，确保数据不会因为系统故障而丢失。
- **支持发布/订阅模式**：Redis内置了发布/订阅模式（Pub/Sub），允许客户端之间通过消息传递通信。这使得Redis可以作为消息队列和实时数据的传输平台。
  [“发-订”模式](https://docs.aws.amazon.com/zh_cn/prescriptive-guidance/latest/cloud-design-patterns/publish-subscribe.html)
- **单线程模型**：尽管Redis是单线程的，但它通过高效的时间驱动模型来处理并发请求，确保了高性能和低延迟。单线程模型也简化了并发控制的复杂性。
- **主从复制**：Redis支持主从复制，可以通过从节点来备份数据或分担读请求，提高数据的可用星星和新系统的伸缩性。
  [主从复制](https://dongzl.github.io/2020/03/15/12-MySQL-Master-Slave-Replication/index.html)
- **应用场景广泛**：Redis被广泛应用于各种场景，包括但不限于缓存系统、会话存储、排行榜、实时分析、地理空间数据索引等。
- **社区支持**：Redis拥有一个活跃的开发者社区，提供大量文档、教程和第三方库，这为开发者提供了强大的支持和丰富的资源。
- **跨平台兼容性**：Redis可以在各种操作系统上与运行，包括Linux、macOS和Windows，这使得它能够在不同的技术栈中灵活部署

# 开始搭建项目

## springboot整合mybatis-plus



1. springboot集成mybatis plus时，对应mapper.xml的位置和内容不确定 

   [^已解决]: 已解决 按照教程步骤，通过CodeGenerator可以生成对应文件和内容

   

2. 整合完mybatis-plus之后，项目无法运行起来，报错：Property 'sqlSessionFactory' or 'sqlSessionTemplate' are required;

   [^已解决]: 这是springboot3版本与低版本mybatis-plus的兼容问题，要将mybatis-plus版本升级，这里还有一个问题就是我单纯的在原项目的pom.xml文件下将版本提升，依然会报错（但类型不同），可能是相关依赖也需要调整。最终是重新构建项目，项目成功启动。

3. 访问[localhost:8080/user/1](http://localhost:8080/user/1)时遇到错误：Whitelabel Error Page This application has no explicit mapping for /error, so you are seeing this as a fallback.

   [^已解决]: 这是由于启动类位置错误引起的，启动类应该放在根目录下，这样才能访问到所有子文件夹。


## 统一结果封装

1. 对统一结果Result类用到的@Data注解不熟悉，其作用是什么？

   [^已解决]: 在项目阅读文档添加了有关Lombok部分的内容

## 整合shiro+jwt，并共享会话



