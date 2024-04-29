package Demo.ZipStreamDemo;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipStreamDemo2 {
    public static void main(String[] args) throws IOException {
        /*
        压缩流（ZIP）压缩
         */
        File src = new File("E:\\Desktop\\aaa");
        Zip(src);
    }

    /*
    * 外部压缩方法
    * src:源文件
    * */
    private static void Zip(File src) throws IOException {
        //创建源文件对应的压缩流
        ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(src.toString() + ".zip"));
        //调用内部压缩方法
        InZip(src,zos,src.getName());
        //释放资源
        zos.close();
    }

    /*
    内部压缩方法
    * src:源文件
    * zos：目标zip文件的压缩流
    * name：子文件夹名称
    * */
    private static void InZip(File src, ZipOutputStream zos,String name) throws IOException {
        File[] files = src.listFiles();
        for (File file : files) {
            if (file.isDirectory()){
                //获取子文件夹名
                InZip(file,zos,name + "\\" + file.getName());
            }else {
                //去根目录
                ZipEntry entry = new ZipEntry(name + "\\" + file.getName());
                //把entry对象添加到压缩文件中
                zos.putNextEntry(entry);
                FileInputStream fis = new FileInputStream(file);
                //定义字节数组
                byte[] bytes = new byte[1024*1024];
                int len;
                while ((len = fis.read(bytes)) != -1){
                    zos.write(bytes,0,len);
                }
                //s释放资源，先开后关
                fis.close();

                zos.closeEntry();
            }
        }
    }
}
