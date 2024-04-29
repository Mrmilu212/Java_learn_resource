package Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class BufferedTest2 {
    public static void main(String[] args) throws IOException {
        /*
        修复文本顺序
        把《出师表》的文章顺序恢复到一个新文件中。
        文件路径：D:\EdgeDownload\csb.txt
         */
        //创建对象并关联文件
        BufferedReader br = new BufferedReader(new FileReader("D:\\EdgeDownload\\csb.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("day28-code\\recover.txt"));

        //创建ArrayList集合
        ArrayList<String> list = new ArrayList<>();
        String s;
        while ((s = br.readLine()) != null){
            list.add(s);
        }
        //排序
        List<String> newList = list.stream().sorted(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String s1 = o1.split("\\.")[0];
                String s2 = o2.split("\\.")[0];
                int a = Integer.parseInt(s1);
                int b = Integer.parseInt(s2);
                return a - b;
            }
        }).toList();

        //写出文件
        for (String str : newList) {
            bw.write(str);
            bw.newLine();
        }
        //释放资源
        bw.close();
        br.close();

    }
}
