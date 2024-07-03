# 一、概述

## 1.Springboot特性

- **起步依赖**:本质上就是一个maven坐标，整合完成一个功能所需要的所有坐标。（解决配置繁琐的问题）
- **自动配置**：遵循约定大于配置的原则，在boot程序启动后，一些bean对象会自动注入到ioc容器，不需要手动声明，简化开发。
- **内嵌的Tomcat、Jetty（无需部署WAR文件）**
- **外部化配置**
- **不需要XML配置**（改用properties/yml）

## 2.配置文件的使用

相对于properties类型的配置文件，yml应用更加广泛。

**yml的优点**：

- 层次清晰
- 关注数据

## 3.配置信息的书写与获取

### 书写

```yaml
email:
	user: 1564785544@qq.com
	code: jfejwezhcrzcbbbb
	host: smtp.qq.com
	auth: true
    
    
#学生爱好——yml的数组表示
hobbies:
	- 打篮球
	- 跑步
	- 。。。
每一个数组项用 '- '表示
```

- 值前边必须有空格，作为分隔符
- 使用空格作为缩进表示层级关系，相同的层级左侧对齐

### 获取

- 方式一：每个值都用一个 '@Value("${键名}")'——麻烦

```java
@Component
public class EmailProperties{
    @Value("${email.user}")
    //发件人邮箱
    public String user;
    
    @Value("${email.code}")
    //授权码（邮件客户端使用）
    public String code;
}
```

- 方式二：使用'@ConfigurationProperties(prefix="前缀")'

  实体类中的成员变量与配置文件中额键名保持一致

```
@Component
@ConfigurationProperties(prefix="email")
public class EmailProperties{
   
    //发件人邮箱
    public String user;
   
    //授权码（邮件客户端使用）
    public String code;
}
```

### 4.整合mybatis

启动时遇到错误：

```xml
You have an error in your SQL syntax; check the manual that corresponds to your MySQL server version for the right syntax to use near 'where id=1' at line 1
; bad SQL grammar []] with root cause
```

[^已解决]: 检查SQL语句是否正确。我这里是写少了表名

```Java
@Mapper
public interface UserMapper {
    @Select("select * from  where id=#{id}")//应该为@Select("select * from user where id=#{id}")
    public User findById(Integer id);
}

```

### 5. Bean管理

#### Bean扫描

**spring**：

- XML标签：<context:component-scan base-package="com.itheima"/>
- 配置类注解：@ComponentScan(basePaclage = "com.itheima")

**Springboot**:

```java
//关键在于@SpringBootApplication
//Springboot默认只能扫描启动类所在的包及其子包
@SpringBootApplication
public class SpringMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisApplication.class, args);
	}

}

```

```java
//@SpringBootApplication的作用等同于下面这三个注解
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan

/*
@ComponentScan注解一般的使用方式是要指定包名的，如
	@ComponentScan(basePaclage = "com.itheima")
在这里没有指定包名，则默认扫描添加了该标签的类所在包及其子包
要想扫描其他的包，可以手动添加
*/
public class SpringMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringMybatisApplication.class, args);
	}

}

```

#### Bean注册

|    注解     | 说明                 | 位置                                            |
| :---------: | :------------------- | ----------------------------------------------- |
| @Component  | 声明Bean的基础注解   | 不属于以下三类时，用此注解                      |
| @Controller | @Component的衍生注解 | 标注在控制器上                                  |
|  @Service   | @Component衍生注解   | 标注在业务类上                                  |
| @Repository | @Component衍生注解   | 注解在数据访问类上（由于与mybatis整合，用的少） |

如果要注册的bean对象来自于第三方（不是自定义的），是无法用@Component及衍生注解声明bean的，这时可以使用：

- @Bean

  ```java 
  //方法一：    
  //可以在启动类中定义方法，返回新创建的对象，springboot会自动将返回值注入到IOC容器中
      //但并不推荐在启动类中注册Bean
      @Bean
      public Country country(){
          return new Country();
      }
  ```

  ```Java
  //方法二：
  @Configuration
  public class CommonConfig {
      //在启动类中定义方法，返回新创建的对象，springboot会自动将返回值注入到IOC容器中
      //不过该种方法不推荐使用，可以另外创建配置类，集中注册Bean
      @Bean
      public Country country(){
          return new Country();
      }
      //对象默认名称是方法名
      //也可以通过@Bean("name")的方式指定对象名称
      //如果方法的内部要使用到ioc中已经存在的bean对象，那么只需要在方法上声明即可，spring会自动注入
      @Bean
      public Province province(Country country){
          System.out.println("province: " + country);
          return new Province();
      }
  }
  
  ```

  **PS**：如果方法的内部要使用到ioc中已经存在的bean对象，那么只需要在方法上声明即可，spring会自动注入

- @Import （该标签在启动类中使用）

  - 导入配置类 （单个）

    ```java
    @Import(Xxx.class)
    //此处的Xxx.class文件可以不在启动类所在的包及其子包下，
    //因为这种方法采用的是手动扫描
    @SpringBootApplication
    public class SpringbootApplication(){
        
    }
    ```

  - 导入 ImportSelector接口实现类

    方法一：直接返回包名数组，不推荐使用

    ```Java
    public class CommonImportSelector implement ImportSelector{
        @Override
        public String[] selectImport(AnnotationMetadata importingClassMetadata){
            //方法一：直接返回包名数组，不推荐使用
            return new String[]{"com.tiheima.config.CommonConfig"};
        }
    }
    ```

    ```Java
    @Import(CommonImportSelector.class)
    @SpringBootApplication
    public class SpringbootApplication(){
        
    }
    ```

    方法二：读取配置文件

    ```Java
    public class CommonImportSelector implement ImportSelector{
        @Override
        public String[] selectImport(AnnotationMetadata importingClassMetadata){
            //方式二：
            //读取配置文件的内容
            List<String> imports = new ArrayList<>();
            //这里使用Java的反射，利用类加载器获取资源，并以流的方式输出
            InputStream is = CommonImportSelector.class.getClassLoader().getResourceAsStream("common.imports");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            try {
                while ((line = br.readLine())!=null){
                    imports.add(line);
                }
    
    
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close();
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return imports.toArray(new String[0]);
        }
    }
    ```

    ```java
    @Import(CommonImportSelector.class)
    @SpringBootApplication
    public class SpringbootApplication(){
        
    }
    ```

  - @EnableXxx注解，封装@Import注解：在方法二的基础上，将调用过程封装为注解

    ```Java
    @Target({ElementType.TYPE})//该注解表示当前注解可以在类上使用
    @Retention(RetentionPolicy.RUNTIME)//该注解表示当前注解会（保留？）在运行时阶段(被调用？)
    @Import(CommonImportSelector.class)
    public @interface EnableCommonConfig {
    }
    ```

    ```Java
    @SpringBootApplication
    @EnableCommonConfig
    public class SpringbootApplication {
    
    }
    ```

  
  #### 注册条件
  
  SpringBoot提供了设置注册生效条件的注解@Conditional

| 注解                      | 说明                                     |
| ------------------------- | ---------------------------------------- |
| @ConditionalOnProperty    | 配置文件中存在对应的属性时，才声明该Bean |
| @ConditionalOnMissingBean | 当不存在当前类型的bean时，才声明该bean   |
| @ConditionalOnClass       | 当前环境存在指定的这个类时，才声明该bean |

```Java
//标注了这些注解后，只有满足了注解的要求，才会将bean注入
//当bean没有被注入时，调用处会报错，没有找到对应的bean
//没有标注这些注解时，如果@Value没有在配置文件中找到值，也会报错
//注意：这些注解做不到配置文件没有找到数据时，将变量设置成默认值
```

### 6.自动配置原理

遵循约定大于配置的原则，在boot程序启动后，起步依赖中的一些bean对象会自动注入到ioc容器中

#### 源码分析

![](https://github.com/Mrmilu212/Java_learn_resource/blob/main/DataSource/Springboot%E7%AC%94%E8%AE%B0/image-20240620145323786.png?raw=true)

#### 实现自动配置的步骤

![](https://github.com/Mrmilu212/Java_learn_resource/blob/main/DataSource/Springboot%E7%AC%94%E8%AE%B0/Snipaste_2024-06-20_15-02-46.png?raw=true)

通过源码分析，我们知道，要实现将自定义的Bean自动配置到Spring的IOC容器中，我们需要在自定义的jar包中提供对应的实体类和配置类信息；同时定义带@AutoConfiguration的自动配置类，利用@Import标签将配置类导入；同时创建配置文件，将上面的自动配置类的全包名添加到配置文件中。实现上述步骤之后，在导入坐标时的时候，Springboot就会扫描添加了@AutoConfiguration标签的配置类，通过对应（Springboot内部实现的）方法将配置类中的bean添加到IOC容器中。

#### 自动配置原理

1. 在启动类上添加SpringbootApplication注解，这个注解组合了EnableAutoConfiguration注解
2. EnableConfiguration注解又组合了Import注解，导入了AutoConfigurationImportSelector类
3. 实现selectImport方法，这个方法经过层层调用，最终会读取META-INF目录下后缀名为.import的文件。在Springboot2.7以前的版本，读取的是spring.factories文件
4. 读取到全类名之后，会解析注册条件，也就是@Condition及其衍生注解，把满足注册条件的Bean对象自动注入到IOC容器中。

#### 自定义mybatis的starter

在实际开发中，经常会定义一些公共组件，提供给各个项目团队使用。而在SpringBoot的项目中，一般会将这些公共的组件封装为SpringBoo的starter

**步骤**：

- 创建dmybatis-spring-boot-autoconfigure模块，提供自动配置功能，并自定义配置文件META-INF/spring/xxx.imports
- 创建dmybatis-spring-boot-starter模块，在starter中引入自动配置模块
