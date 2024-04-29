package Test;

import java.io.*;

public class IOTest2 {
    public static void main(String[] args) throws IOException {
        /*
        为了保证文件的安全性，就需要对原始文件进行加密存储，再使用的时候再对器进行解密处理
        加密原理：
                对原始文件中的每一个字节数据进行更改，然后将更改后的数据存储到新的文件中。
        解密原理：
                读取加密之后的文件，按照加密的规则反向操作，变成原始文件
         */

        encrypt(new File("day26-code\\b.txt"));
        //encrypt(new File("day26-code\\b.txt"));
    }

    public static void encrypt(File src) throws IOException {
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream("day26-code\\c.txt");
        int b;

        while ((b = fis.read()) != -1) {
            // 对read的返回值进行加一操作，以达到加密的目的
            fos.write(b ^ 114515);
        }

        fos.close();
        fis.close();
    }

}
