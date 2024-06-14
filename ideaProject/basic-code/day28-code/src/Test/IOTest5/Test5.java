package Test.IOTest5;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Test5 {
    public static void main(String[] args) throws IOException {
        /*
        TXT文件中事先准备号一些学生信息，每个学生的信息独占一行
        要求1：每次被点到的学生，再次被点到的概率在原先的基础上降一半
        举例：80个学生，点名五次，每次都点到小A，概率变化情况如下：
            第一次每人的概率：1.25% (1/80)
            第二次小A的概率：0.625%             其他学生概率：1.2579%
            第三次小A的概率：0.3125%            其他学生概率：1.261867%
            第四次小A的概率：0.15625%           其他学生概率：1.2638449%
            第五次小A的概率：0.078125%          其他学生概率：1.26483386%

        提示：本题核心就是带权重的随机

        结论：该算法无法完美符合概率，存在偏差
         */
        //创建文件对象并关联本地文件
        File src = new File("day28-code\\src\\Test\\IOTest5\\students.txt");
        weightsRollCall(src);
    }

    public static void weightsRollCall(File src) throws IOException {
        //读取数据
        ArrayList<Student> list = readFile(src);
        //获取权值总和
        double totalWeights = 0;
        for (Student s : list) {
            totalWeights += s.getWeights();
        }

        //创建随机数
        Random r = new Random();
        //获取带权索引
        double index = r.nextDouble(totalWeights);
        //从零索引开始计算权值范围，直到确定index对应的元素
        double weights = 0;
        for (Student stu : list) {
            //获取权值区间
            double before = weights;
            double after = weights + stu.getWeights();
            //确认index是否在区间内
            if (index > before && index <= after) {
                //输出点到的名字
                System.out.println(stu.getName());
                //修正权值
                correctWeights(stu);
                //将修改后的数据写回文件中
                writeFile(list, src);

                System.out.println(totalWeights);
                System.out.println(before);
                System.out.println(index);
                System.out.println(after);

                //退出循环
                break;
            }
            weights += stu.getWeights();
        }

    }


    private static void correctWeights(Student stu) {
        //将记录的权值变为原来的一半
        double weights = stu.getWeights() / 2;
        stu.setWeights(weights);
    }

    public static ArrayList<Student> readFile(File file) throws IOException {
        //创建字符缓冲输入流
        BufferedReader br = new BufferedReader(new FileReader(file));
        //创建集合
        ArrayList<Student> list = new ArrayList<>();
        String str;
        while ((str = br.readLine()) != null) {

            String[] info = str.split("-");
            String name = info[0];
            String gender = info[1];
            int age = Integer.parseInt(info[2]);
            double weights = Double.parseDouble(info[3]);

            list.add(new Student(name, gender, age, weights));
        }
        //释放资源
        br.close();
        return list;
    }

    public static void writeFile(ArrayList<Student> list, File file) throws IOException {
        //创建字符缓冲输出流
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        //写入数据
        for (Student stu : list) {
            //直接调用重写后的toString方法
            bw.write(stu.toString());
            bw.newLine();
        }
        //释放资源
        bw.close();

    }


}
