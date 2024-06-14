package Test;

public class TreadTest1 {
    public static void main(String[] args) {
        //线程的安全问题
        //需求：
        //某电影院目前正在上映国产大片，共有100张票，而它有3个买票的窗口，请设计一个程序模拟该电影院买票
        //设置变量表示票数

        //创建对象
        Sole s1 = new Sole();
        Sole s2 = new Sole();
        Sole s3 = new Sole();

        //起名字
        s1.setName("售货员1");
        s2.setName("售货员2");
        s3.setName("售货员3");

        //开启进程
        s1.start();
        s2.start();
        s3.start();

    }

}
