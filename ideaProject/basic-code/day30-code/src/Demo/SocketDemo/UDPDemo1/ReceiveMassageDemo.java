package Demo.SocketDemo.UDPDemo1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ReceiveMassageDemo {
    public static void main(String[] args) throws IOException {
        //接收数据
        //1.创建DatagramSocket对象
        //细节：接收的端口要与绑定的端口一致
        DatagramSocket ds = new DatagramSocket(8080);

        //2.创建DatagramPacket对象，用于接收数据
        byte[] bytes = new byte[1024];
        DatagramPacket dp = new DatagramPacket(bytes,bytes.length);

        //该方法事阻塞的
        //在接收到数据之前不会执行下面的代码
        ds.receive(dp);

        //3.获取数据
        InetAddress address = dp.getAddress();
        byte[] data = dp.getData();
        int port = dp.getPort();
        int len = dp.getLength();

        //控制台输出数据

        System.out.println("接收到的信息为：" + new String(data,0,len));
        System.out.println("来自主机 " + address + " 的 " + port + " 端口" );

        //释放资源
        ds.close();

    }
}
