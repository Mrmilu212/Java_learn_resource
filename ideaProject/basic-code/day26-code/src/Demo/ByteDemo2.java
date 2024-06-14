package Demo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ByteDemo2 {
    public static void main(String[] args) throws IOException {
        /*
        换行写：
              windows: \r\n
              linux:   \n
              mac:     \r
              Java底层对换行符优化了，无论是输入\r或者\n都可以表示换行，java会自动补全

              建议：
                  新手书写时尽量不要省略

        续写：
            在创建对象时，将第二个参数设置为true，默认为false
         */
        //创建对象
        FileOutputStream fos = new FileOutputStream("day27-code//a.txt",true);
        //定义字符串对象，调用getBytes函数
        String str1 = "nihao";
        byte[] bytes1 = str1.getBytes();
        //写入数据
        fos.write(bytes1);

        //写入换行符
        String str = "\r\n";
        byte[] bytes = str.getBytes();
        fos.write(bytes);

        String str2 = "dajiahao";
        byte[] bytes2 = str2.getBytes();
        //写入数据
        fos.write(bytes2);

        //释放资源
        fos.close();


    }
}
