package Demo.ZipStreamDemo;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipStreamDemo1 {
    public static void main(String[] args) throws IOException {
        /*
        压缩流（zip）解压
         */
        //创建对象并关联文件
        File src = new File("E:\\Desktop\\aaa.zip");
        File dest = new File("E:\\Desktop");

        unzip(src,dest);
    }

    private static void unzip(File src, File dest) throws IOException {
        //创建压缩流
        ZipInputStream zip = new ZipInputStream(new FileInputStream(src));
        //获取压缩文件的每一个ZipEntry对象
        ZipEntry entry ;
        while ((entry = zip.getNextEntry()) != null){
            if (entry.isDirectory()){
                //在目的位置创建新的文件夹
                File file = new File(dest,entry.toString());
                file.mkdirs();
            }else {
                //创建字节输出流，将文件拷贝到目的位置
                FileOutputStream fos = new FileOutputStream(new File(dest,entry.toString()));
                byte[] bytes = new byte[1024*1024];
                int len;
                while ((len = zip.read(bytes)) != -1){
                    fos.write(bytes,0,len);
                }
                fos.close();
            }
        }
        zip.close();
    }
}
