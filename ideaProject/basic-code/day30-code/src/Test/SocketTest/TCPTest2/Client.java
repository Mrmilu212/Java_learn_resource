package Test.SocketTest.TCPTest2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        //创建socket对象
        Socket socket = new Socket("127.0.0.1",10086);
        //发送数据
        //获取输出流
        OutputStream os = socket.getOutputStream();
        //打包数据
        String massage = "发送了一条消息";
        byte[] bytes = massage.getBytes(StandardCharsets.UTF_8);
        os.write(bytes);

        //结束发送数据
        socket.shutdownOutput();

        //开始读取回写数据
        InputStream is = socket.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //接收数据
        //定义数组，用于提高信息传输速率
        char[] ch = new char[1024];
        int len;
        while ((len = br.read(ch)) != 0 && len != -1){
            System.out.println("服务端：" + new String(ch,0,len));
        }

        os.close();
        br.close();
        is.close();
        socket.close();
    }
}
