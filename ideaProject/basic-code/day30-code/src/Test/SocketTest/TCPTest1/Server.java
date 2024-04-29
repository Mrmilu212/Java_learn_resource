package Test.SocketTest.TCPTest1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        //创建对象
        ServerSocket ss = new ServerSocket(10086);

        //等待连接
        Socket socket = ss.accept();

        //获取输入流
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        char[] bytes = new char[1024];
        int len;
        while ((len = br.read(bytes)) != 0 && len != -1){
            System.out.println(new String(bytes,0,len));
        }

        //释放资源
        br.close();
        is.close();
        socket.close();
        ss.close();
    }
}
