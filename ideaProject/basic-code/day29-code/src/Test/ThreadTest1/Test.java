package Test.ThreadTest1;

public class Test {
    public static void main(String[] args) {
        /*
        多线程练习1（卖电影票）
        一共有1000张电影票，可以在两个窗口领取，假设每次领取的时间为3000毫秒
        要求：请用多线程模拟买票的过程并打印剩余电影票的数量
         */
        //创建对象
        TicketOutlets to1 = new TicketOutlets();
        TicketOutlets to2 = new TicketOutlets();

        //设置名称
        to1.setName("售票口1");
        to2.setName("售票口2");

        //开启线程
        to2.start();
        to1.start();
    }
}
