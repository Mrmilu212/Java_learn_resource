package Demo;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ConvertStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
            利用转换流按照指定字符编码读取（了解）
            因为JDK11：这种方式被淘汰了。替代方案需要掌握

            D:\EdgeDownload\gbkfile.txt
         */
        FileReader fr = new FileReader("D:\\EdgeDownload\\gbkfile.txt", Charset.forName("GBK"));
        int ch;
        while ((ch = fr.read()) != -1){
            System.out.print((char) ch);
        }
        fr.close();
    }
}
