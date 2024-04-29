package Test.ReflectTest.ReflectTest2;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class ReflectTest2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        /*
        反射可以跟配置文件结合的方式，动态的创建对象，并调用方法
        */
        //1.获取配置文件的信息
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("day30-code\\src\\Test\\ReflectTest\\ReflectTest2\\prop.properties");
        //加载配置文件
        prop.load(fis);
        fis.close();
        System.out.println(prop);

        //2.获取全类名和方法名
        String className = (String) prop.get("class");
        String methodName = (String) prop.get("method");

        System.out.println(className);
        System.out.println(methodName);
        //3.利用反射创建对象并运行方法
        Class clazz = Class.forName(className);

        //获取构造方法
        Constructor con = clazz.getDeclaredConstructor();
        Object o = con.newInstance();
        System.out.println(o);

        //获取成员方法并运行
        Method method = clazz.getDeclaredMethod(methodName);
        method.setAccessible(true);
        //invoke方法的参数是前面利用构造方法创建的对象 o
        method.invoke(o);
    }
}
