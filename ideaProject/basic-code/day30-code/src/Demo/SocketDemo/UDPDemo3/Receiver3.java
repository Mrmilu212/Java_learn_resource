package Demo.SocketDemo.UDPDemo3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Receiver3 {
    public static void main(String[] args) throws IOException {
        //组播
        //1.创建MulticastSocket 对象
        MulticastSocket ms = new MulticastSocket(10086);

        //将本机添加到224.0.0.1分组里
        InetAddress address = InetAddress.getByName("224.0.0.2");
        ms.joinGroup(address);

        //2.接收数据
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);

        ms.receive(dp);

        InetAddress adr = dp.getAddress();
        String hostAddress = adr.getHostAddress();
        String hostName = adr.getHostName();
        int length = dp.getLength();
        byte[] data = dp.getData();

        System.out.println("从地址" + hostAddress + "名为" + hostName + "的主机接收到消息：" + new String(data,0,length));

        //3.释放资源
        ms.close();
    }
}
