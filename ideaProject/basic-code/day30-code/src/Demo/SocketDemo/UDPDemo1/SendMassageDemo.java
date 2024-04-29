package Demo.SocketDemo.UDPDemo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendMassageDemo {
    public static void main(String[] args) throws IOException {
        //发送数据

        //1.创建DatagramSocket对象（快递公司）

        DatagramSocket ds = new DatagramSocket();

        //2.打包数据
        //数据
        String str = "雷猴啊";
        byte[] bytes = str.getBytes();
        //地址和端口
        InetAddress address = InetAddress.getByName("127.0.0.1");
        int port = 8080;

        DatagramPacket dp = new DatagramPacket(bytes,bytes.length,address,port);

        //发送数据
        ds.send(dp);

        //4.释放资源
        ds.close();
    }
}
