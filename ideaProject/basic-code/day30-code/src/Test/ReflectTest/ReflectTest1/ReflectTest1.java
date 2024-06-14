package Test.ReflectTest.ReflectTest1;

import java.io.*;
import java.lang.reflect.Field;

public class ReflectTest1 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, IOException {
        /*
        对任意一个对象，都可以把对象的所有字段名和值保存到文件中去
         */
        //获取class对象
        Student s = new Student("小A",23,'女',167.5,"睡觉");
        Teacher t = new Teacher("张三",10000);

        //定义方法
        saveData(t);
    }

    private static void saveData(Object o) throws IllegalAccessException, IOException {
        Class clazz = o.getClass();
        //获取成员变量
        Field[] fields = clazz.getDeclaredFields();
        //创建输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter("day30-code\\src\\Test\\ReflectTest\\ReflectTest1\\data.txt"));
        for (Field field : fields) {
            //暂时取消访问权限
            field.setAccessible(true);
            String name = field.getName();
            //get() 方法的参数是对象
            Object value = field.get(o);
            bw.write(name + "=" + value);
            bw.newLine();
        }

        bw.close();
    }
}
