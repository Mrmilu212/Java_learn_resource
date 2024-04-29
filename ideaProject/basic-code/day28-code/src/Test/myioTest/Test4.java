package Test.myioTest;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Test4 {
    public static void main(String[] args) throws IOException {
        /*
        需求：
            一个文件里面存储了班级同学的姓名，每一个姓名占一行
            要求通过程序实现随机点名器


        运行效果：
        被点到的学生不会再被点到
        如果班级中所以学生都点完了，需要自动开始第二轮点名
        细节1：假设班级有10个学生，每一轮中每一位同学只能被点到一次，程序 运行 10次，第一轮结束
        细节2：第11次 运行 的时候，我们不需要手动操作本地文件，要求程序自动开始第二轮点名
         */
        //创建文件对象，并关联本地文件
        File names = new File("day28-code\\src\\Test\\myioTest\\allnames.txt");
        File bu = new File("day28-code\\src\\Test\\myioTest\\backup.txt");

        //读取文件信息
        ArrayList<String> allNames = readFile(names);
        ArrayList<String> backup = readFile(bu);

        //如果存储学生姓名的文件为空
        if (allNames.size() == 0){
            System.out.println("开始新一轮点名");
            //将备份中的数据写回名单中
            writeFile(backup,names);
            //清空备份文件
            new FileOutputStream(bu);

            //重新读取数据
            allNames = readFile(names);
            backup = readFile(bu);
        }
        System.out.println(allNames.size());
        System.out.println(backup.size());
        //开始点名
        //创建随机数
        Random r = new Random();

        int index = r.nextInt(allNames.size());
        //将随机到的名字从列表总移除
        String name = allNames.remove(index);
        System.out.println(name.split("-")[0]);

        //将名字添加到备份集合中
        backup.add(name);

        //将两个集合中的数据写回文件
        writeFile(allNames,names);
        writeFile(backup,bu);



    }

    public static ArrayList<String> readFile(File file) throws IOException {
        //创建字符缓冲输入流
        BufferedReader br = new BufferedReader(new FileReader(file));
        //创建集合
        ArrayList<String> list = new ArrayList<>();
        String str;
        while ((str = br.readLine()) != null){
            list.add(str);
        }
        //释放资源
        br.close();
        return list;
    }

    public static void writeFile(ArrayList<String> list , File file) throws IOException {
        //创建字符缓冲输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        //写入数据
        for (String s : list) {
            bw.write(s);
            bw.newLine();
        }
        //释放资源
        bw.close();

    }
}
