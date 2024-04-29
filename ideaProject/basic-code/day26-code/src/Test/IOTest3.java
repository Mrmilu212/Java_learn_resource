package Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class IOTest3 {
    public static void main(String[] args) throws IOException {
        /*
        修改文件中的数据
        文本文件中有以下的数据
        2-1-9-4-7-8
        将文件中的数据进行排序，变成以下的数据：
        1-2-4-7-8-9
         */
        //1.读取数据
        FileInputStream fis = new FileInputStream("day26-code\\b.txt");

        //定义StringBuilder对象
        StringBuilder sb = new StringBuilder();
        //创建ArrayList集合，用于排序
        ArrayList<Character> list = new ArrayList<>();
        //获取元素，并添加到集合中
        int b;
        while ((b = fis.read()) != -1) {
            if (b >= '0' && b <= '9')
                list.add((char) b);
        }
        fis.close();
        //2.处理数据并将数据写入文档
        FileOutputStream fos = new FileOutputStream("day26-code\\b.txt");
        //排序
        list.sort((s1,s2) -> s1 - s2);
        //拼接成字符串
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1)
                sb.append(list.get(i)).append("-");
            else sb.append(list.get(i));
        }

        //将拼接后的字符串转换为字节数组
        byte[] bytes = sb.toString().getBytes();
        fos.write(bytes);

        fos.close();

    }
}
