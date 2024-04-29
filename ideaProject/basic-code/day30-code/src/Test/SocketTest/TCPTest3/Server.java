package Test.SocketTest.TCPTest3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class Server {
    public static void main(String[] args) throws IOException {
        /*
        客户端：将本地文件上传到服务器。接收服务器的反馈
        服务端：接收客户端上传到的文件，上传完毕后给出反馈
         */

        //创建ServerSocket对象，并绑定端口
        ServerSocket ss = new ServerSocket(10086);

        //等待客户端连接
        Socket socket = ss.accept();

        //读取数据
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        //写入文件

        //解决文件重名的问题
        String name = UUID.randomUUID().toString().replace("-", "");

        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("day30-code\\src\\Test\\TCPTest3\\"+ name +".txt"));
        byte[] bytes = new byte[1024 * 1024];
        int len;
        while ((len = bis.read(bytes)) != -1 && len != 0) {
            bos.write(bytes, 0, len);
        }
        bos.flush();
        bos.close();
        //结束接收数据
        socket.shutdownInput();

        //发送反馈消息
        String massage = "数据已上传";
        OutputStream os = socket.getOutputStream();
        os.write(massage.getBytes());

        //释放资源
        os.close();
        bis.close();
        socket.close();
        ss.close();
    }
}
