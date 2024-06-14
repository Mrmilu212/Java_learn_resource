package Test;

import java.io.*;
import java.lang.annotation.Target;

public class IOTest1 {
    public static void main(String[] args) throws IOException {
        /*
        拷贝一个文件夹,考虑子文件夹
         */
        //创建文件对象
        //被复制文件夹
        File src = new File("E:\\Desktop\\aaa");
        //目标文件夹
        File target = new File("E:aaa");

        copyDirectory(src, target);
    }

    private static void copyDirectory(File src, File target) throws IOException {
        //创建目标文件夹
        target.mkdirs();
        File[] files = src.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    //创建输入输出字节流对象
                    FileInputStream fis = new FileInputStream(file);
                    //利用parent、child的构造方法来创建来文件
                    FileOutputStream fos = new FileOutputStream(new File(target, file.getName()));
                    //定义字节数组,提高拷贝速率
                    byte[] bytes = new byte[1024 * 1024];
                    int len;
                    while ((len = fis.read(bytes)) != -1) {
                        fos.write(bytes, 0, len);
                    }

                    fos.close();
                    fis.close();
                } else if (file.isDirectory()) {
                    //定义子文件夹
                    copyDirectory(file, new File(target, file.getName()));
                }

            }
        }
    }
}
