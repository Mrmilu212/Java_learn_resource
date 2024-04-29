package Demo;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class ChatSetDemo1 {
    public static void main(String[] args) throws UnsupportedEncodingException {
        /*
        Java中的编码方法
              public byte[] getBytes()                          使用默认方式进行编码
              public byte[] getBytes()                          使用指定方式进行编码

        Java中解码的方法
              String(byte[] bytes)                              使用默认方式进行编码
              String(byte[] bytes,String charsetName)           使用指定方式进行编码
         */

        String str = "ai你哟";
        byte[] bytes1 = str.getBytes();//使用默认方式编码
        System.out.println(Arrays.toString(bytes1));

        byte[] bytes2 = str.getBytes("GBK");//使用GBK方式进行编码
        System.out.println(Arrays.toString(bytes2));


        //解码
        String str1 = new String(bytes1);//UTF-8形式编码,UTF-8形式解码
        String str2 = new String(bytes1,"GBK");//以UTF-8方式编码，GBK方式编码

        System.out.println(str1);
        System.out.println(str2);

        System.out.println("你好");

    }
}
