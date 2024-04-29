package Demo.myCharStream;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CharStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
        字符流 空参读取
         */

        //创建对象并关联本地文件
        //参数可以是文件对象,也可以是表示文件路径的字符串
        FileReader fr = new FileReader("day26-code\\a.txt");
        //2.读取数据 read()
        //read()细节:
        //1.read():默认也是一个字节一个字节地读取,如果遇到中文会一次读取多个
        //2.在读取之后,方法底层还会进行解码转成十进制
        //  最终会把这个十进制作为返回值
        //  这个十进制的数据也表示在字符集上的数字
        int ch;
        while ((ch = fr.read()) != -1){
            System.out.print((char) ch);
        }

        //释放资源
        fr.close();
    }
}
