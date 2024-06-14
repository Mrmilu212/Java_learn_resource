package Test.SocketTest.TCPTest2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建ServerSocket对象
        ServerSocket ss = new ServerSocket(10086);

        //接收客户端的数据
        Socket socket = ss.accept();
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        //提高读取速率
        char[] chs = new char[1024];
        int len;
        //如果客户端不关闭输出流，服务端就会继续在 read() 方法等待客户端写出数据
        while ((len = br.read(chs)) != 0 && len != -1){
            System.out.println("客户端：" + new String(chs,0,len));
        }
        //结束接受数据
        socket.shutdownInput();

        //回写数据
        OutputStream os = socket.getOutputStream();
        //打包数据
        String massage = "确认了一条消息";
        byte[] bytes = massage.getBytes(StandardCharsets.UTF_8);
        os.write(bytes);


        //释放资源
        br.close();
        is.close();
        os.close();
        socket.close();
        ss.close();


    }
}
