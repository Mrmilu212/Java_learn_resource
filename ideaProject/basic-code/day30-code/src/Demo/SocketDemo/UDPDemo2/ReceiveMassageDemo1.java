package Demo.SocketDemo.UDPDemo2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveMassageDemo1 {
    public static void main(String[] args) throws IOException {
        //接收方

        //1.创建对象，并绑定对应端口
        DatagramSocket ds = new DatagramSocket(10086);

        //2.接收数据
        //创建数据用于接收数据
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);
        while (true) {
            ds.receive(dp);

            //获取数据
            byte[] data = dp.getData();
            int length = dp.getLength();
            InetAddress address = dp.getAddress();
            int port = dp.getPort();

            System.out.println("从主机" + address + "的" + port + "端口接收到数据：" + new String(bytes,0,length));
        }


    }
}
