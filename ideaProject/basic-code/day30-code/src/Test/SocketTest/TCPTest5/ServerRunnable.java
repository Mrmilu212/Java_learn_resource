package Test.SocketTest.TCPTest5;

import java.io.*;
import java.net.Socket;
import java.util.UUID;

public class ServerRunnable implements Runnable{

    Socket socket;

    public ServerRunnable(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            //读取数据
            BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
            //写入文件

            //解决文件重名的问题
            String name = UUID.randomUUID().toString().replace("-", "");

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("day30-code\\src\\Test\\TCPTest5\\serverDir\\"+ name +".mp4"));
            byte[] bytes = new byte[1024 * 1024];
            int len;
            while ((len = bis.read(bytes)) != -1 && len != 0) {
                bos.write(bytes, 0, len);
            }
            bos.flush();
            bos.close();
            //结束接收数据
            socket.shutdownInput();

            //发送反馈消息
            String massage = "数据已上传";
            OutputStream os = socket.getOutputStream();
            os.write(massage.getBytes());

            //释放资源
            os.close();
            bis.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
