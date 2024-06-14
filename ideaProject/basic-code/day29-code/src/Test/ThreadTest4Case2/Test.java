package Test.ThreadTest4Case2;

public class Test {
    public static void main(String[] args) {
        /*
        抢红包也用到了多线程
        假设：100块，分成了3个红包，现在有5个人去抢
        其中，红包是共享数据
        5人是五条线程
        打印结果如下：
            XXX抢到了XXX元
            XXX抢到了XXX元
            XXX抢到了XXX元
            XXX没抢到
            XXX没抢到
         */
        Person p1 = new Person();
        Person p2 = new Person();
        Person p3 = new Person();
        Person p4 = new Person();
        Person p5 = new Person();

        //设置名字
        p1.setName("张三");
        p2.setName("李四");
        p3.setName("王五");
        p4.setName("赵六");
        p5.setName("李华");

        //启动线程
        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
    }
}
