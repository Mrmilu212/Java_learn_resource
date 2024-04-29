package Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class IOTest4 {
    public static void main(String[] args) throws IOException {
        /*
        test3优化
         */
        //读取数据
        FileReader fr = new FileReader("day26-code\\a.txt");
        StringBuilder sb = new StringBuilder();
        int ch;
        while ((ch = fr.read()) != -1){
            sb.append((char) ch);
        }
        fr.close();
        System.out.println(sb);
        //排序
        Integer[] arr = Arrays.stream(sb.toString().split("-"))
                .map(Integer::parseInt)
                .sorted()
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr));
        //写出
        FileWriter fw = new FileWriter("day26-code\\a.txt");
        //将arr转换为字符串后替换符号
        String s = Arrays.toString(arr).replace(", ", "-");
        //剪切字符串，删除前后的方括号
        String result = s.substring(1, s.length() - 1);
        fw.write(result);


        fw.close();

    }
}
