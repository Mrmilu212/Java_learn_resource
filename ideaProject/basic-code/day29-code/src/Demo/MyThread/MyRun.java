package Demo.MyThread;

public class MyRun implements Runnable{
    @Override
    public void run() {
        //获取当前运行的线程
        Thread t = Thread.currentThread();
        for (int i = 0; i < 100; i++) {
            System.out.println(t.getName() + "天生我才必有用");
        }
    }
}
