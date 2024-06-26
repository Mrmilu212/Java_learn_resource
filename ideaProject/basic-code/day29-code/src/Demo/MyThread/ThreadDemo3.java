package Demo.MyThread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class ThreadDemo3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
        * 多线程的第三种实现方式：
        *   特点：可以获取多线程运行的结果
        *
        *   1.创建一个类MyCallable实现Callable接口
        *   2.重写call（有返回值，，表示多线程运行的结果）
        *
        *   3.创建MyCallable的对象（表示多线程要执行的任务）
        *   4.创建FutureTask的对象（作用是管理多线程运行的结果）
        *   5.创建Thread类的对象，并启动（表示线程）
        * */

        /** Callable 接口获取线程名字需要调用Thread.currentThread.getName()方法 **/

        //创建MyCallable的对象（表示多线程要执行的任务）
        MyCallable mc = new MyCallable();

        //创建FutureTask对象
        FutureTask<Integer> ft = new FutureTask<>(mc);

        //创建多线程对象
        Thread t1 = new Thread(ft);
        //启动线程
        t1.start();

        //获取多线程运行的结果
        Integer result = ft.get();
        System.out.println(result);
    }
}
