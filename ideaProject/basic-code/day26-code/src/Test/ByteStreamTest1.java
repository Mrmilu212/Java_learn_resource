package Test;

import java.io.*;

public class ByteStreamTest1 {
    public static void main(String[] args) throws IOException {
        /*
        将文件 从 D:\EdgeDownload\“悬溺一响，夏目登场”\“悬溺一响，夏目登场”(360P).mp4
        中拷贝到 当前项目下的 copy.mp4中

        练习：
            统计拷贝的用时
         */
        //创建对象
        FileInputStream fis = new FileInputStream("D:\\EdgeDownload\\“悬溺一响，夏目登场”\\“悬溺一响，夏目登场”(360P).mp4");
        FileOutputStream fos = new FileOutputStream("day26-code\\copy.mp4");

        //获取拷贝开始前的时间
        long start = System.currentTimeMillis();
        //创建字节数组
        byte[] bytes = new byte[1024*1024*5];
        int len;//获取数组中元素的个数
        while ((len = fis.read(bytes)) != -1){
            fos.write(bytes,0,len);
        }

        //获取拷贝结束后的时间
        long end = System.currentTimeMillis();

        System.out.println(end - start);

        //先创建，后关闭
        fos.close();
        fis.close();
    }
}
