package Demo.myCharStream;

import java.io.FileWriter;
import java.io.IOException;

public class ChatSetDemo3 {
    public static void main(String[] args) throws IOException {
        /*
            第一步:创建对象
            public FileWriter(File file)                            创建字符流输出并关联本地文件
            public FileWriter(String pathname)                      创建字符流输出并关联本地文件
            public FileWriter(File file , boolean append)           创建字符流输出并关联本地文件,续写打开
            public FileWriter(String pathname , boolean append)     创建字符流输出并关联本地文件,续写打开

            第二步:读取数据
            void write(int c)                                       写出一个字符
            void write(String str)                                  写出一个字符串
            void write(String str,int off , int len)                写出一个字符串的一部分
            void write(char[] cbuf)                                 写出一个字符数组
            void write(char[] cbuf , int off ,int len)              写出字符数组的一部分

            第三步:释放资源
            public void close()                     释放资源/关流

            '我'                                 25105
         */
        FileWriter fw = new FileWriter("day26-code\\a.txt");


    }
}
