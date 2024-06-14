package Test.IOTest6;

import java.io.*;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) throws IOException {
        /*
        需求：
            将正确的用户名和密码手动保存在本地的userinfo.txt文件中
            保存格式为：username=zhangsan&password=123
            让用户键盘录入用户名和密码
            比较用户录入的和正确的用户名密码是否一直
            如果一致则打印登录成功
            如果不一致则打印登录失败

         */
        //创建字节缓冲输出流并关联文件
        BufferedReader br = new BufferedReader(new FileReader("day28-code\\src\\Test\\IOTest6\\userinfo.txt"));
        //用户输入数据
        Scanner sc = new Scanner(System.in);

        System.out.println("请输入用户名");
        String username = sc.nextLine();

        System.out.println("请输入密码");
        String password = sc.nextLine();

        //利用用户提供的数据暂时创建一个用户对象
        User user = new User(username,password);
        String str;
        //获取文件数据
        while ((str = br.readLine()) != null){
            if (str.equals(user.toString())){
                System.out.println("登录成功");
                return;
            }
        }
        System.out.println("登录失败");
        br.close();
    }
}
