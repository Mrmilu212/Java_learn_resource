package Test.SocketTest.TCPTest5;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    public static void main(String[] args) throws IOException {
        /*
        多个用户同时上传，线程池优化
         */

        //创建线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                3,//核心线程数量
                12,//线程池中最大线程数量
                60,//空闲时间的值
                TimeUnit.SECONDS,//空闲时间的单位
                new ArrayBlockingQueue<>(3),//阻塞队列大小
                Executors.defaultThreadFactory(),//创建线程的方式
                new ThreadPoolExecutor.AbortPolicy()//要执行的任务过多时的解决方案
        );
        //创建ServerSocket对象，并绑定端口
        ServerSocket ss = new ServerSocket(10086);

        while (true){
            //等待客户端连接并启动线程
            //new ServerThread(ss.accept()).start();

            pool.submit(new ServerRunnable(ss.accept()));
        }
    }
}
