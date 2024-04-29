package Demo;

import java.io.FileInputStream;
import java.io.IOException;

public class ByteDemo3 {
    public static void main(String[] args) throws IOException {
        /*
        fis.read()
         */
        FileInputStream fis = new FileInputStream("day26-code\\a.txt");
        int len;
        byte[] bytes = new byte[6];
        len = fis.read(bytes);
        System.out.println(len);
        len = fis.read(bytes);
        System.out.println(len);

        fis.close();
    }
}
