package Test;

import java.io.*;

public class BufferedTest1 {
    public static void main(String[] args) throws IOException {
        /*
        拷贝文件
        四种方式拷贝文件，并统计各自的用时

        字节流的基本流：一次读写一个字节
        字节流的基本流：一次读写一个字节数组
        字节缓冲流：一次读写一个字节
        字节缓冲流：一次读写一个字节数组

        目标文件 : D:\EdgeDownload\“悬溺一响，夏目登场”\“悬溺一响，夏目登场”(360P).mp4

         */
        //字节流的基本流：一次读写一个字节
        System.out.println(
                byteCopyCost(
                        new File("D:\\EdgeDownload\\“悬溺一响，夏目登场”\\“悬溺一响，夏目登场”(360P).mp4")));
        //字节流的基本流：一次读写一个字节数组
        System.out.println(
                bytesCopyCost(
                        new File("D:\\EdgeDownload\\“悬溺一响，夏目登场”\\“悬溺一响，夏目登场”(360P).mp4")));
        //字节缓冲流：一次读写一个字节
        System.out.println(
                bufferedByteCopyCost(
                        new File("D:\\EdgeDownload\\“悬溺一响，夏目登场”\\“悬溺一响，夏目登场”(360P).mp4")));
        //字节缓冲流：一次读写一个字节数组
        System.out.println(
                bufferedBytesCopyCost(
                        new File("D:\\EdgeDownload\\“悬溺一响，夏目登场”\\“悬溺一响，夏目登场”(360P).mp4")));

    }

    //字节流的基本流：一次读写一个字节
    public static long byteCopyCost(File src) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream("day28-code\\copy1.mp4");
        //一次一字节
        int b;
        while ((b = fis.read()) != -1) {
            fos.write(b);
        }
        fos.close();
        fis.close();

        long end = System.currentTimeMillis();

        return end - start;
    }

    //字节流的基本流：一次读写一个字节数组
    public static long bytesCopyCost(File src) throws IOException {
        long start = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(src);
        FileOutputStream fos = new FileOutputStream("day28-code\\copy2.mp4");
        //一次一字节数组
        byte[] bytes = new byte[1024*5];
        int len;
        while ((len = fis.read(bytes)) != -1) {
            fos.write(bytes,0,len);
        }
        fos.close();
        fis.close();

        long end = System.currentTimeMillis();

        return end - start;
    }

    //字节缓冲流：一次读写一个字节
    public static long bufferedByteCopyCost(File src) throws IOException {
        long start = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("day28-code\\copy3.mp4"));
        //一次一字节
        int b;
        while ((b = bis.read()) != -1) {
            bos.write(b);
        }
        bos.close();
        bis.close();

        long end = System.currentTimeMillis();

        return end - start;
    }

    //字节缓冲流：一次读写一个字节数组
    public static long bufferedBytesCopyCost(File src) throws IOException {
        long start = System.currentTimeMillis();
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("day28-code\\copy4.mp4"));
        //一次一字节数组
        byte[] bytes = new byte[1024*5];
        int len;
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        bos.close();
        bis.close();

        long end = System.currentTimeMillis();

        return end - start;
    }
}
