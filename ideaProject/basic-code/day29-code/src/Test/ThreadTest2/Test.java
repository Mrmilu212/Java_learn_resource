package Test.ThreadTest2;

public class Test {
    public static void main(String[] args) {
        /*
        有100份礼品，两人同时发送，当剩下的礼品小于10份时不再送出。
        利用多线程模拟该过程并将线程的名字和礼品剩余的数量打印出来
         */
        //创建对象
        Person p1 = new Person();
        Person p2 = new Person();

        //设置名称
        p1.setName("小明");
        p2.setName("小鸿");

        //开启线程
        p1.start();
        p2.start();
    }
}
