package Test.myioTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Test2 {
    public static void main(String[] args) throws IOException {
        /*
        需求：有一个文件里存储了班级同学的信息，每个信息占一行。
        格式：张三-男-23
        要求通过程序实现随机点名器

        运行效果：
            第一次运行程序：随机铜须姓名1（只显示姓名）
            第二次运行程序：随机铜须姓名2（只显示姓名）
            第三次运行程序：随机铜须姓名3（只显示姓名）
         */
        //创建字符缓冲输入流并关联文件
        BufferedReader br = new BufferedReader(new FileReader("day28-code\\name.txt"));
        //创建ArrayList集合，用于保存读取到的数据
        ArrayList<String> list = new ArrayList<>();
        String str;
        while ((str = br.readLine()) != null){
            list.add(str);
        }
        //释放资源
        br.close();
        //创建随机数
        Random r = new Random();
        int index = r.nextInt(list.size());
        //只显示名字
        System.out.println(list.get(index).split("-")[0]);


    }
}
