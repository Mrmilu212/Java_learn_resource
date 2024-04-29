package Demo.SocketDemo.UDPDemo2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class SendMassageDemo1 {
    public static void main(String[] args) throws IOException {
        //实现聊天室

        //发送方
        //1.创建DatagramSocket对象
        DatagramSocket ds = new DatagramSocket();

        Scanner sc = new Scanner(System.in);

        while (true) {
            //2.打包数据
            String str = sc.nextLine();
            if (str.equals("886")){
                break;
            }
            byte[] bytes = str.getBytes();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            int port = 10086;

            DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port);
            //3.发送数据
            ds.send(dp);
        }

        //发送数据
        ds.close();

    }
}
