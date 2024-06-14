package Test.myioTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Test3 {
    public static void main(String[] args) throws IOException {
        /*
        需求：
        一个文件里存储了班级同学的信息，每个信息占一行。
        格式：张三-男-23
        要求通过程序实现随机点名器

        运行效果：
            70%的概率随机到男生
            30%的概率随机到女生
            总共随机100万次，通统计结果
         */
        //创建字符缓冲输入流
        BufferedReader br = new BufferedReader(new FileReader("day28-code\\name.txt"));
        //创建两个集合，分别保存男女姓名
        ArrayList<String> boyList = new ArrayList<>();
        ArrayList<String> girlList = new ArrayList<>();

        //读取数据
        String str;
        while ((str = br.readLine()) != null){
            if (str.split("-")[1].equals("男")){
                boyList.add(str);
            }else {
                girlList.add(str);
            }
        }
        //释放资源
        br.close();

        //创建随机数
        Random r = new Random();
        //定义变量记录随机到男女的次数
        int boyCount = 0;
        int girlCount = 0;
        //自定义模拟权重
        for (int j = 0 ; j < 1000000 ; j++) {
            int index = r.nextInt(10) + 1;
            if (index > 7){
                int i = r.nextInt(girlList.size());
                System.out.println(girlList.get(i).split("-")[0]);
                girlCount++;
            }else {
                int i = r.nextInt(boyList.size());
                System.out.println(boyList.get(i).split("-")[0]);
                boyCount++;
            }
        }
        System.out.println(boyCount);
        System.out.println(girlCount);
        //System.out.println(boyList.size());
        //System.out.println(girlList.size());

    }
}
