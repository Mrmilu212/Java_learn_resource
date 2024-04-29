package Demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteDemo1 {
    public static void main(String[] args) throws IOException {
        /*
        void write(int b)                           一次写一个字节的数据
        void write(byte[] b)                        一次写一个字节数组数据
        void write(byte[] b, int off , int len)     一次写一个字节数组的部分数据
        参数的含义：
        b：数组
        off：偏移量（可以理解为起始索引）
        len：需要写入的个数
         */
        //创建对象
        FileOutputStream fos = new FileOutputStream("day27-code//a.txt");
        //写出数据
        byte[] bytes = {97,98,99,100,101};

        fos.write(bytes);
        //释放资源
        fos.close();
    }
}
