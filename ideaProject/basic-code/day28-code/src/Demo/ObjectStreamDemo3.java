package Demo;

import java.io.*;
import java.util.ArrayList;

public class ObjectStreamDemo3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
        需求：
            将多个自定义的对象序列化到本地文件中，但是由于对象的个数不确定，反序列化流该如何读取呢？
         */
        //创建序列化流对象
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("day28-code\\a.txt"));
        //创建集合
        ArrayList<Student> list = new ArrayList<>();
        //添加元素
        list.add(new Student("zhangsan",23));
        list.add(new Student("lisi",24));
        list.add(new Student("wangwu",25));
        list.add(new Student("zhaoliu",26));
        list.add(new Student("demu",2));
        //序列化对象
        oos.writeObject(list);
        //释放资源
        oos.close();

    }
}
