package Test.ThreadTest1;

public class TicketOutlets extends Thread {
    //定义静态变量，表示本类所有对象共用
    static int ticket = 1000;

    @Override
    public void run() {
        while (true) {
            synchronized (Thread.class) {
                if (ticket > 0) {

                    //领票花费3000毫秒
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ticket--;
                    System.out.println(getName() + ":剩余" + ticket + "张票");

                } else break;
            }
        }
    }
}
