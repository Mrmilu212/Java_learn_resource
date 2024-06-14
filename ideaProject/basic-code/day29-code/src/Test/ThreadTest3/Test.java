package Test.ThreadTest3;

public class Test {
    public static void main(String[] args) {
        /*
        打印奇数数字
        同时开启两个线程，共同获取1-100之间的所有数字
        要求：打印输出所有奇数
         */

        //创建对象
        OddPrinter op1 = new OddPrinter();
        OddPrinter op2 = new OddPrinter();

        //开启线程
        op2.start();
        op1.start();
    }
}
