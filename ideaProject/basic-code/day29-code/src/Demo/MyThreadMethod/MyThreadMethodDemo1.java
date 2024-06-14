package Demo.MyThreadMethod;

public class MyThreadMethodDemo1 {
    public static void main(String[] args) {
        /*
        String getName()                            返回此线程的名称
        void setName(String)                        设置线程的名字（默认Thread-1,2,3,4... ）

        static Thread currentThread()               获取当前线程的对象（我们每次运行代码时，都是运行在JVM默认的main线程中）
        static void sleep(long time)                让线程休眠指定时间，单位为毫秒
        setPriority(int newPriority)                设置线程的优先级（最小为1，最大为10，默认为5）
        final int getPriority()                     获取线程的优先级（抢到CPU的概率大小）
        final int setDaemon(boolean on)             设置为守护线程（当其他非守护线程执行完毕之后，守护线程会陆续结束（可能不会执行完整代码））
        public static void yield()                  出让线程\礼让线程（让出当前CPU的执行权）
        public static void join()                   插入线程\插队线程
                                                    （表示把线程t，插入到当前线程之前。当前线程 指正在运行代码线程
                                                    如：t.join 写在 main方法 里，那么当前线程就是 main线程）
         */
        //线程的安全问题
        //需求：
        //某电影院目前正在上映国产大片，共有100张票，而它有3个买票的窗口，请设计一个程序模拟该电影院买票
        //设置变量表示票数
        int ticket = 100;


    }
}
