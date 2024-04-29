package Demo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

public class ConvertStreamDemo2 {
    public static void main(String[] args) throws IOException {
        /*
        替换文件的编码形式
        D:\EdgeDownload\gbkfile.txt
         */
        FileReader fr  = new FileReader("D:\\EdgeDownload\\gbkfile.txt", Charset.forName("GBK"));
        FileWriter fw = new FileWriter("day28-code\\a.txt", Charset.forName("UTF-8"));

        int ch;
        while ((ch = fr.read()) != -1){
            fw.write(ch);
        }

        fw.close();
        fr.close();
    }
}
