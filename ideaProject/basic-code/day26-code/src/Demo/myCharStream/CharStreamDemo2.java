package Demo.myCharStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CharStreamDemo2 {
    public static void main(String[] args) throws IOException {
        /*
        字符流(只能操作纯文本文件) 有参读取
         */
        //创建对象
        FileReader fr = new FileReader("day26-code\\a.txt");

        //创建字符数组,用于一次读取多个数据
        char[] chs = new char[2];
        int len;//字符数组chs里的字符个数
        //read(chars) 把读取数据,解码,强制类型转换合并为一个操作

        while ((len = fr.read(chs)) != -1){
            System.out.print(new String(chs,0,len));
        }

        //释放资源
        fr.close();
    }
}
