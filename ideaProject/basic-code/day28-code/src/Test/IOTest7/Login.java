package Test.IOTest7;

import java.io.*;
import java.util.Scanner;
import java.util.StringJoiner;

public class Login {
    public static void main(String[] args) throws IOException {
        /*
        需求：写一个登录小案例（添加锁定账号功能）

        步骤：
            将正确的用户名和密码手动保存在本地的userinfo.txt中。
            保存格式：username=zhangsan&password=123&count=0
            让用户键盘录入用户名和密码
            比较用户录入的和正确的用户名和密码是否一致
            如果一致则打印登录成功
            如果不一致则打印登录失败，连续输错三次被锁定
         */
        //读取文件数据
        BufferedReader br = new BufferedReader(new FileReader("day28-code\\src\\Test\\IOTest7\\userinfo.txt"));
        String[] userinfo = br.readLine().split("&");
        br.close();
        String rightName = userinfo[0].split("=")[1];
        String rightPassword = userinfo[1].split("=")[1];
        int count = Integer.parseInt(userinfo[2].split("=")[1]);

        //用户输入信息
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入用户名");
        String username = sc.nextLine();
        System.out.println("请输入密码");
        String password = sc.nextLine();
        if (username.equals(rightName)&& count == 3){
            System.out.println("你的账户已经被锁定");
        }else if (username.equals(rightName)&&password.equals(rightPassword)){
            System.out.println("登录成功");
            count = 0;
        }else if (username.equals(rightName)&&!password.equals(rightPassword)){
            System.out.println("密码错误，请重新输入");
            count = count + 1;
        }else {
            System.out.println("用户不存在");
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter("day28-code\\src\\Test\\IOTest7\\userinfo.txt"));
        StringJoiner sj = new StringJoiner("&","","");
        userinfo[2] = "count=" + count;
        for (String s : userinfo) {
            sj.add(s);
        }

        bw.write(sj.toString());
        bw.close();
    }
}
