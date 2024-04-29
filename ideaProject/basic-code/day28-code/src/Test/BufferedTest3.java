package Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedTest3 {
    public static void main(String[] args) throws IOException {
        /*
        实现一个验证程序运行次数的小程序，要求如下：
            1.当程序运行超过3次时给出提示：本软件只能免费使用3次，欢迎你注册会员后继续使用~
            2.程序运行演示如下：
                第一次运行控制台输出：欢迎使用本软件，第1次使用免费~
                第二次运行控制台输出：欢迎使用本软件，第2次使用免费~
                第三次运行控制台输出：欢迎使用本软件，第3次使用免费~
                第四次及之后运行控制台输出:本软件只能免费使用3次，欢迎您注册会员后继续使用~
         */
        FileInputStream fis = new FileInputStream("day28-code\\log.txt");
        int b = fis.read();
        if ((b - '0') != 3){
            b++;
            System.out.println("欢迎使用本软件，第"+ (b - '0') +"次使用免费~");
        }else System.out.println("本软件只能免费使用3次，欢迎您注册会员后继续使用~");

        fis.close();
        FileOutputStream fos = new FileOutputStream("day28-code\\log.txt");
        fos.write(b);

        fos.close();
        fis.close();
    }
}
