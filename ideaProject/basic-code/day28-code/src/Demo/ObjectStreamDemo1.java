package Demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
        需求：
            利用序列化流/对象操作输出流，把一个对象写到本地文件中

        构造方法：
            public ObjectOutputStream(OutputStream out)             把基本流变成高级流

        成员方法：
            public final void writeObject(Object obj)               把对象序列化（写出）到文件中去
         */
        //创建序列流并关联文件
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("day28-code\\a.txt"));
        //创建学生对象
        Student stu = new Student("zhangsan",23);
        //序列化对象
        oos.writeObject(stu);
        //释放资源
        oos.close();
    }
}
