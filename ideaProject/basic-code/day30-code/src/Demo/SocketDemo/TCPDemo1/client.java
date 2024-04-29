package Demo.SocketDemo.TCPDemo1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class client {
    public static void main(String[] args) throws IOException {
        //客户端
        //1.创建客户端的Socket对象（Socket）与指定服务器连接
        Socket socket = new Socket("127.0.0.1",10086);

        //获取输出流，写数据
        OutputStream os = socket.getOutputStream();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String data = sc.nextLine();
            if (data.equals("#"))
                break;
            byte[] bytes = data.getBytes();
            os.write(bytes);
        }
        //释放资源
        os.close();
        socket.close();

    }
}
