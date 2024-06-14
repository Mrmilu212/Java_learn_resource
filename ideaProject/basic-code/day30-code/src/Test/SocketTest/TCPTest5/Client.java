package Test.SocketTest.TCPTest5;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        /*
        客户端：将本地文件上传到服务器。接收服务器的反馈
        服务端：接收客户端上传到的文件，上传完毕后给出反馈
         */
        //创建Socket对象，与服务端建立连接
        Socket socket = new Socket("127.0.0.1", 10086);

        //读取本地文件
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("day30-code\\src\\Test\\TCPTest5\\clientDir\\“悬溺一响，夏目登场”(360P).mp4"));
        //将文件写到服务器中
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        byte[] bytes = new byte[1024 * 1024];
        int len;
        while ((len = bis.read(bytes)) != 0 && len != -1) {
            bos.write(bytes, 0, len);
        }
        bos.flush();
        bis.close();
        //结束发送数据
        socket.shutdownOutput();

        //接收反馈消息
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String massage = br.readLine();
        System.out.println(massage);

        //释放资源
        br.close();
        is.close();
        socket.close();
    }
}
