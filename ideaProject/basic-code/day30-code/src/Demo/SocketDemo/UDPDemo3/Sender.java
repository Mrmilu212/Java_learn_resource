package Demo.SocketDemo.UDPDemo3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Sender {
    public static void main(String[] args) throws IOException {
        //发送端
        //创建对象
        MulticastSocket ms = new MulticastSocket();

        //打包数据
        String str = "空吧哇";
        byte[] bytes = str.getBytes();
        InetAddress address = InetAddress.getByName("224.0.0.2");
        int port = 10086;
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port);

        //发送数据
        ms.send(dp);

        //释放资源
        ms.close();
    }
}
