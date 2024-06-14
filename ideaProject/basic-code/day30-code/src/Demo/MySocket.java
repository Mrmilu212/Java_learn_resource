package Demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class MySocket {
    public static void main(String[] args) throws UnknownHostException {
        /*
        static InetAddress getByName(String host)           确定主机名称的IP地址，主机名称可以是机器名称，也可以是IP地址
        String getHostName()                                获取此IP的主机名称
        String getHostAddress()                             返回文本显示中的IP地址字符串
         */

        InetAddress address = InetAddress.getByName("LAPTOP-JCL9B5V7");
        System.out.println(address);

        String ip = address.getHostName();
        System.out.println(ip);
    }
}
