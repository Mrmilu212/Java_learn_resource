package Demo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectStreamDemo2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        /*
        需求：
            利用反序列流\对象操作输入流，把文件中中的对象读到程序当中
        构造方法：
            public ObjectInputStream(InputSteam out)            把基本流变成高级流
        成员方法：
            public Object readObject()                          把序列化到本地文件中的对象，读到程序中
         */
        //1.创建反序列化流对象
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("day28-code\\a.txt"));
        //2.读取数据
        Student student = (Student) ois.readObject();
        //打印对象
        System.out.println(student);
        //释放资源

    }
}
