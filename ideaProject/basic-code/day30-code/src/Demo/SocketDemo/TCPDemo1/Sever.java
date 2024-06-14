package Demo.SocketDemo.TCPDemo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Sever {
    public static void main(String[] args) throws IOException {
        //服务端
        //1.创建服务端的Socket对象（ServerSocket）
        ServerSocket ss = new ServerSocket(10086);

        //等待连接
        Socket socket = ss.accept();

        InputStream is = socket.getInputStream();
        InetAddress inetAddress = socket.getInetAddress();
        int port = socket.getPort();

        System.out.print("从地址" + inetAddress + "的" + port + "端口接收到数据:");

        //处理中文乱码问题
        //使用转换流
        InputStreamReader isr = new InputStreamReader(is);
        //提高效率
        BufferedReader br = new BufferedReader(isr);

        int b;
        while ((b = br.read()) != -1){
            System.out.print((char) b);
        }
        System.out.println();
        //释放资源
        is.close();
        //断开连接
        socket.close();

        ss.close();
    }
}
