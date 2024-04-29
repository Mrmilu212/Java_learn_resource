package Demo.DynamicproxyDemo.DynamicproxyDemo1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {
    private ProxyUtil() {
    }

    public static Star createProxy(BigStar bigStar){
        /*java.lang.reflect.Proxy类提供了为对象产生代理对象的方法：
        * public static object newProxyInstance(ClassLoader loader,Class<?>[] interfaces,InvocationHandler h)
        * 参数一：用于指定用哪个类的加载器，去加载生成的代理类
        * 参数二：指定接口，这些接口用于指定生成的代理长什么样，也就是有哪些方法
        * 参数三：用来指定生成的代理对象要干什么事情
        * */
        Star star = (Star) Proxy.newProxyInstance(
                ProxyUtil.class.getClassLoader(),//参数一：用于指定用哪个类的加载器，去加载生成的代理类
                new Class[]{Star.class},//参数二：指定接口，这些接口用于指定生成的代理长什么样，也就是有哪些方法
                new InvocationHandler() {//参数三：用来指定生成的代理对象要干什么事情
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName().equals("sing")){
                            System.out.println("准备场地，收钱");
                        }else if (method.getName().equals("dance")){
                            System.out.println("准备场地，收钱");
                        }

                        return method.invoke(bigStar,args);
                    }
                }
        );
        return star;
    }

}
