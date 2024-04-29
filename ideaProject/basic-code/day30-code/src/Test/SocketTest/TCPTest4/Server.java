package Test.SocketTest.TCPTest4;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {
    public static void main(String[] args) throws IOException {
        /*
        多个用户同时上传
         */

        //创建ServerSocket对象，并绑定端口
        ServerSocket ss = new ServerSocket(10086);

        while (true){
            //等待客户端连接并启动线程
            new ServerThread(ss.accept()).start();
        }
    }
}
