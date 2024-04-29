package Test.SocketTest.TCPTest1;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        //创建Socket对象
        Socket socket = new Socket("127.0.0.1",10086);

        Scanner sc = new Scanner(System.in);
        //获取输出流
        OutputStream os = socket.getOutputStream();

        while (true){
            String massage = sc.nextLine();
            //设定结束条件
            if (massage.equals("#")){
                break;
            }
            byte[] bytes = massage.getBytes(StandardCharsets.UTF_8);
            os.write(bytes);
        }

        //释放资源
        os.close();
        socket.close();
    }
}
